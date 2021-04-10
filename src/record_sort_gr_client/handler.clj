(ns record-sort-gr-client.handler
  (:require [record-sort-gr-client.add-view :as add]
            [record-sort-gr-client.main-view :as main]
            [record-sort-gr-client.order-view :as order]
            [record-sort-gr-client.records :as recs]))

(defn main-view [req]
  (let [records (recs/read-recs)]
    {:status 200
     :headers {}
     :body (main/page records)}))

(defn add-view [req]
  {:status 200
   :headers {}
   :body (add/page)})

(defn order-view [req]
  {:status 200
   :headers {}
   :body (order/page)})

(defn sort-order [req]
  (let [req-order (get-in req [:params "sort-order"])
        order (recs/set-sort-order req-order)]
    (if order
      {:status 302
       :headers {"Location" "/"}
       :body (str "Sort order set to " order)}
      {:status 400
       :headers {}
       :body (str "Invalid sort order: " req-order)})))

(defn create [req]
  (let [error (recs/create nil nil nil nil nil)]
    (if error
      {:status 400
       :headers {}
       :body error}
      {:status 302 ;; 201
       :headers {"Location" "/"}
       :body "New records created"})))

(defn reset [req]
  (let [error (recs/reset)]
    (if error
      {:status 500
       :headers {}
       :body error}
      {:status 302
       :headers {"Location" "/"}
       :body "Records reset to empty"})))
