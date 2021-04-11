(ns record-sort-gr-client.add-view
  (:require [hiccup.core :refer [html h]]
            [hiccup.page :refer [html5]]))

(defn page [error-strings]
  (html5
   {:lang "en"}
   
   [:head
    [:meta {:charset "utf-8"}]]
   
   [:body
    (when error-strings
      [:div
       [:h1 "Errors"]
       (into [:ul] (for [s error-strings]
                     [:li s]))])
 
    [:h1 "Add a Record"]
    [:form {:method "POST" :action "/"}
     [:div
      [:label {:for "fname"} "First Name"]
      [:input {:type "text" :name "fname" :id "fname"}]]
     [:div 
      [:label {:for "lname"} "Last Name"]
      [:input {:type "text" :name "lname" :id "lname"}]]
     [:div 
      [:label {:for "color"} "Favorite Color"]
      [:input {:type "text" :name "color" :id "color"}]]
     [:button {:type "submit"} "OK"]]
    [:form {:method "GET" :action "/"}
     [:button {:type "submit"} "Cancel"]] ]))
