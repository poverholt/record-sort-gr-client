(ns record-sort-gr-client.add-view
  (:require [hiccup.core :refer [html h]]
            [hiccup.page :refer [html5]]))

(defn page [error-strings]
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
     
     [:h2 "Add a Record"]
     [:form {:method "POST" :action "/"}
      [:div.form-group
       [:label {:for "fname"} "First Name"]
       [:input.form-control {:type "text" :name "fname" :id "fname"}]]
      [:div.form-group
       [:label {:for "lname"} "Last Name"]
       [:input.form-control {:type "text" :name "lname" :id "lname"}]]
      [:div.form-group
       [:label {:for "gender"} "Gender"]
       [:input.form-control {:type "text" :name "gender" :id "gender"}]]
      [:div.form-group 
       [:label {:for "color"} "Favorite Color"]
       [:input.form-control {:type "text" :name "color" :id "color"}]]
      [:div.form-group
       [:label {:for "bdate"} "Birthdate"]
       [:input.form-control {:type "text" :name "bdate" :id "bdate"}]]     
      [:button.btn.btn-primary {:type "submit"} "OK"]]
     [:form {:method "GET" :action "/"}
      [:div.form-group
       [:button.btn.btn-primary {:type "submit"} "Cancel"]] ]]]))
