(ns record-sort-gr-client.core
  (:require [compojure.core :refer [defroutes ANY DELETE GET POST PUT]]
            [compojure.route :refer [not-found]]
            [ring.adapter.jetty :as jetty]
            [record-sort-gr-client.handler :as hdlr]
            [ring.handler.dump :refer [handle-dump]]
            [ring.middleware.reload :refer [wrap-reload]]))

(defroutes routes
  (GET "/" [] hdlr/home)
  (GET "/add" [] hdlr/add)
  (PUT "/sort" [] hdlr/sort)
  (POST "/" [] hdlr/create)
  (DELETE "/" [] hdlr/reset)
  (ANY "/request" [] handle-dump)
  (not-found "Route not found."))

(defn -main [port]
  (jetty/run-jetty greet                 {:port (Integer. port)}))

(defn -dev-main [port]
  (jetty/run-jetty (wrap-reload #'routes) {:port (Integer. port)}))
