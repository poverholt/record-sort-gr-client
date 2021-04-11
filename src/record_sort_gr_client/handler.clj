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
  ;; TODO: Parse out delimiter and fields!
  (let [error (recs/create-rec! "|" nil nil nil nil nil)]
    (if error
      {:status (:status error)
       :headers {"Location" "/add"}
       :body (add/page [(str "HTTP error status: " (:status error))
                        (str "Reason: " (:reason-phrase error))
                        (str "Details: " (:body error))])}
      {:status 201
       :headers {"Location" "/"}
       :body "New record created"})))

(defn reset [req]
  (let [error (recs/reset-recs!)]
    (if error
      {:status 500
       :headers {}
       :body (str "HTTP error status: " error)}
      {:status 302
       :headers {"Location" "/"}
       :body "Records reset to empty"})))
