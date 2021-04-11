(ns record-sort-gr-client.handler
  (:require [record-sort-gr-client.add-view :as add]
            [record-sort-gr-client.main-view :as main]
            [record-sort-gr-client.order-view :as order]
            [record-sort-gr-client.records :as recs]))

(defn- errors->strvec
  [error]
  [(str "HTTP error status: " (get error :status "N/A"))
   (str "Reason: " (get error :reason-phrase "N/A"))
   (str "Details: " (get error :body "N/A"))])
  
(defn main-view [req]
  (let [results (recs/read-recs)]
    (if (:status results)
      {:status (:status results)
       :headers {}
       :body (main/page nil (errors->strvec results))}
      {:status 200
       :headers {}
       :body (main/page results nil)})))

(defn add-view [req]
  {:status 200
   :headers {}
   :body (add/page nil)})

(defn order-view [req]
  {:status 200
   :headers {}
   :body (order/page)})

(defn sort-order [req]
  (let [req-order (get-in req [:params "sort-order"])
        order (recs/set-sort-order! req-order)]
    (if order
      {:status 302
       :headers {"Location" "/"}
       :body (str "Sort order set to " order)}
      {:status 400
       :headers {}
       :body (str "Invalid sort order: " req-order)})))

(defn create [req]
  (let [lname (get-in req [:params "lname"])
        fname (get-in req [:params "fname"])
        gender (get-in req [:params "gender"])
        color (get-in req [:params "color"])
        bdate (get-in req [:params "bdate"])
        error (recs/create-rec! "|" lname fname gender color bdate)]
    (if error
      {:status (:status error)
       :headers {"Location" "/add"}
       :body (add/page (errors->strvec error))}
      {:status 302
       :headers {"Location" "/"}})))

(defn reset [req]
  (let [error (recs/reset-recs!)]
    (if error
      {:status 500
       :headers {}
       :body (str "HTTP error status: " error)}
      {:status 302
       :headers {"Location" "/"}
       :body "Records reset to empty"})))
