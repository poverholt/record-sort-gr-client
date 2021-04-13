(ns record-sort-gr-client.core
  (:require [compojure.core :refer [defroutes ANY DELETE GET POST PUT]]
            [compojure.route :refer [not-found]]
            [ring.adapter.jetty :as jetty]
            [record-sort-gr-client.handler :as hdlr]
            [record-sort-gr-client.records :as recs]
            [ring.handler.dump :refer [handle-dump]]
            [ring.middleware.params :refer [wrap-params]]
            [ring.middleware.reload :refer [wrap-reload]]))

(defroutes routes
  (GET "/" [] hdlr/main-view)
  (GET "/add" [] hdlr/add-view)
  (GET "/sort" [] hdlr/order-view)
  (PUT "/sort" [] hdlr/sort-order)
  (POST "/" [] hdlr/create)
  (DELETE "/" [] hdlr/reset)
  ;;(ANY "/request" [] handle-dump)
  (not-found "Route not found."))

(defn wrap-server [hdlr]
  (fn [req]
    (assoc-in (hdlr req) [:headers "Server"] "Record Sort GR Client Server")))

(defn wrap-simulated-methods [hdlr]
  (let [sim-methods {"PUT" :put
                     "DELETE" :delete}]
    (fn [req]
      (if-let [method (and (= :post (:request-method req))
                           (sim-methods (get-in req [:params "_method"])))]
        (hdlr (assoc req :request-method method))
        (hdlr req)))))

(def app
  (wrap-server
   (wrap-params
    (wrap-simulated-methods
     routes))))

(defn -main [& [port]]
  (let [port (Integer. (or (System/getenv "PORT") port 8010))]
    (jetty/run-jetty app {:port port})))

(defn -dev-main [port]
  (jetty/run-jetty (wrap-reload #'app) {:port (Integer. port)}))
