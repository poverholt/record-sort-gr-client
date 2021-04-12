(ns record-sort-gr-client.order-view
  (:require [hiccup.core :refer [html h]]
            [hiccup.page :refer [html5]]))

(defn page []
  (html5
   {:lang "en"}
   
   [:head
    [:meta {:charset "utf-8"}]]

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
     [:h2 "Sort Order"]
     [:form {:method "POST" :action "/sort"}
      [:input {:type :hidden
               :name "_method"
               :value "PUT"}]
      [:div.form-check
       [:label.form-check-label
        [:input.form-check-input {:type "radio" :name "sort-order" :value "gender" :checked true}]
        "Gender"]]
      [:div.form-check
       [:label.form-check-label
        [:input.form-check-input {:type "radio" :name "sort-order" :value "birthdate" :checked false}]
        "Birthdate"]]
      [:div.form-check
       [:label.form-check-label
        [:input.form-check-input {:type "radio" :name "sort-order" :value "name" :checked false}]
       "Name"]]
      [:button.btn.btn-primary {:type "submit"} "OK"]]]]))
