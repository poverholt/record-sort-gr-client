(ns record-sort-gr-client.handler)

(defn home [req]
  {:status 200
   :body "Home response in handler"
   :headers {}})

(defn add [req]
  {:status 200
   :body "Add response"
   :headers {}})

(defn sort [req]
  {:status 200
   :body "Sort response"
   :headers {}})

(defn create [req]
  {:status 200
   :body "Create record response"
   :headers {}})

(defn reset [req]
  {:status 200
   :body "Reset records response"
   :headers {}})