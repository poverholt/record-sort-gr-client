(ns record-sort-gr-client.main-view
  (:require [hiccup.core :refer [html h]]
            [hiccup.page :refer [html5]]))

(defn page [recs error-strings]
  (html5
   {:lang "en"}
   
   [:head
    [:title "Record Sorting"]
    [:meta {:charset "utf-8"}]
    [:meta {:name "viewport"
            :content "width=device-width, initial-scale=1.0, shrink-to-fit=no"}]
    [:link {:rel "stylesheet"
            :href "https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
            :integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
            :crossorigin="anonymous"}]]
   
   [:body
    [:div.container.pt-3
     (when error-strings
       [:div.text-danger
        [:h2 "Errors"]
        (into [:ul] (for [s error-strings]
                      [:li s]))])
     
     [:h2 "Record Sorting"]

     [:div
      [:form {:method "GET" :action "/add"}
       [:div.form-group
        [:button.btn.btn-secondary {:type "submit"} "Add"]]]
      [:form {:method "POST" :action "/"}
       [:div.form-group
        [:input {:type :hidden
                 :name "_method"
                 :value "DELETE"}]
        [:button.btn.btn-secondary {:type "submit"} "Reset"]]]
      [:form {:method "GET" :action "/sort"}
       [:div.form-group
        [:button.btn.btn-secondary {:type "submit"} "Sort"]]]]

     [:div
      (into [:table.table]
            (for [rec recs]
              [:tr
               [:td (:lname rec)]
               [:td (:fname rec)]
               [:td (:gender rec)]
               [:td (:color rec)]
               [:td (:bdate rec)]]))]
     [:div
      [:p (str "Record Count: " (count recs) " records")]]]
    
    [:script {:src "https://code.jquery.com/jquery-3.2.1.slim.min.js"
              :integrity "sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
              :crossorigin "anonymous"}]
    [:script {:src "https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
              :integrity "sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
              :crossorigin "anonymous"}]
    [:script{:src "https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
             :integrity "sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
             :crossorigin "anonymous"}]
    ]))
