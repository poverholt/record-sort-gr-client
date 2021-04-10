(ns record-sort-gr-client.add-view
  (:require [hiccup.core :refer [html h]]
            [hiccup.page :refer [html5]]))

(defn page []
  (html5
   {:lang "en"}
   
   [:head
    [:meta {:charset "utf-8"}]]
   
   [:body
    [:div
     [:h1 "Add a Record"]]]))
