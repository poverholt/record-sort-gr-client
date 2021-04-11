(ns record-sort-gr-client.records
  "Access to the record sort server that keeps and sorts the data."
  (:require [cheshire.core :as cheshire]
            [clj-http.client :as client]))

(def host "http://localhost:8000")

;; I doubt we need this
(def server nil)

(def state (atom {:sort-order "gender"}))

(def sort-orders #{"gender",     ;; Female then male, then last name ascending
                   "birthdate",  ;; Birth date ascending
                   "name"})      ;; Last name descending

(defn set-sort-order!
  "Returns new sort order as a string. Nil indicates error."
  [req-order]
  (let [order (sort-orders req-order)]
    (when order
      (swap! state assoc :sort-order order))
    order))

(defn client-exception-handler
  [action]
  (try
    (action)
    (catch java.net.ConnectException e
      {:status 503
       :headers {}
       :body "Connection to server failed."})
    (catch Exception e
      (select-keys (ex-data e) [:status :reason-phrase :body]))))

(defn create-rec!
  "Creates one record.
   Delimiter may be pipe, comma or space character.
   Gender may be F, Female, M or Male.
   Birthdata format is MM/DD/YYYY.
   Returns nil if no error and returns map with HTTP error fields :status, :reason-phrase and :body."
  [delimiter lname fname gender color bdate]
  (letfn [(action [] (do (client/post (str host "/records")
                                      {:form-params {:data (str lname delimiter
                                                                fname delimiter
                                                                gender delimiter
                                                                color delimiter
                                                                bdate)}})
                         nil))]
    (client-exception-handler action)))

(defn read-recs
  "Returns Clojure data structure, a vector of maps, where each map has 5 keys:
   lname, fname, gender, color, birthdate. All 5 values are strings.
   Returns nil if there was an error."
  []
  (letfn [(action [] (cheshire/parse-string (client/get (str host "/records/" (:sort-order @state)) {:accept :json})))]
    (client-exception-handler action)))

(defn reset-recs!
  "Resets record list to empty.
   Returns nil if no error and returns HTTP status otherwise."
  []
  (letfn [(action [] (do (client/delete (str host "/records"))
                         nil))]
    (client-exception-handler action)))

;; (reset-recs!)

;; (read-recs)

;; (cheshire/parse-string (client/get (str host "/records/" (:sort-order @state)) {:accept :json}))
;; (set-sort-order! "name")

;; (create-rec! "|" "Smith" "John" "M" "Blue" "4/3/2001")

;; (create-rec! " " nil nil nil nil nil)


