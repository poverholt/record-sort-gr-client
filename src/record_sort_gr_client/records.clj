(ns record-sort-gr-client.records
  "Access to the record sort server that keeps and sorts the data.")

;; I doubt we need this
(def server nil)

(def state (atom {:sort-order "gender"}))

(def sort-orders #{"gender",     ;; Female then male, then last name ascending
                   "birthdate",  ;; Birth date ascending
                   "name"})      ;; Last name descending

(defn set-sort-order [sort-order] (sort-orders sort-order))

(defn create [lname fname gender color bdate] "Missing fields.")

(defn read-recs [] []) ;; TODO: Returns empty list for now

(defn reset [] "ERROR: Not supported")



