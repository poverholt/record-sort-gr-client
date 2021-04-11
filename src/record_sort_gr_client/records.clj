(ns record-sort-gr-client.records
  "Access to the record sort server that keeps and sorts the data."
  (:require [clj-http.client :as client]))

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

(defn create-rec!
  "Creates one record.
   Delimiter may be pipe, comma or space character.
   Gender may be F, Female, M or Male.
   Birthdata format is MM/DD/YYYY.
   Returns nil if no error and returns HTTP status otherwise."
  [delimiter lname fname gender color bdate]
  (let [result (client/post
                (str host "/records")
                {:form-params {:data (str lname delimiter
                                          fname delimiter
                                          gender delimiter
                                          color delimiter
                                          bdate)}})
        status (:status result)]
    (if (= status 201) nil status)))

(defn read-recs
  "Returns Clojure data structure, a vector of maps, where each map has 5 keys:
   lname, fname, gender, color, birthdate. All 5 values are strings.
   Returns nil if there was an error."
  []
  (let [result (client/get (str host "/records/" (:sort-order @state)) {:accept :json})
        status (:status result)
        records (:body result)]
    (if (= status 200) records nil)))

(defn reset-recs!
  "Resets record list to empty.
   Returns nil if no error and returns HTTP status otherwise."
  []
  (let [result (client/delete (str host "/records"))
        status (:status result)]
    (if (= status 200) nil status)))

;; (reset-recs!)

;; (read-recs)

;; (set-sort-order! "name")

;; (create-rec! "|" "Smith" "John" "M" "Blue" "4/3/2001")


