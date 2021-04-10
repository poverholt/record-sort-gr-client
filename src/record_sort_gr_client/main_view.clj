(ns record-sort-gr-client.main-view
  (:require [hiccup.core :refer [html h]]
            [hiccup.page :refer [html5]]))

(defn page [recs]
  (html5
   {:lang "en"}
   
   [:head
    [:meta {:charset "utf-8"}]]

   ;; [:head
   ;;  [:title "Record Sorting"]
   ;;  [:meta {:name :viewport
   ;;          :content "width=device-width, initial-scale=1.0"}]
   ;;  [:link {:hef "/bootstrap/css/bootstrap.min.css"
   ;;          :rel :stylesheet}]]
   
   [:body
    [:h1 "Record Sorting"]
    [:p (str "There are " (count recs) " records.")]
    [:div
     [:form {:method "GET" :action "/add"}
      [:input {:type "button" :name "add-btn"}]]
     [:form {:method "DELETE" :action "/"}
      [:input {:type "button" :name "reset-btn"}]]
     [:form {:method "PUT" :action "/sort"}
      [:div
       [:input {:type "radio" :id "radio-gender" :name "sort-order" :value "gender" :checked true}]
       [:label {:for "radio-gender"} "Gender"]]
      [:div
       [:input {:type "radio" :id "radio-gender" :name "sort-order" :value "gender" :checked true}]
       [:label {:for "radio-gender"} "Gender"]]
      [:div
       [:input {:type "radio" :id "radio-gender" :name "sort-order" :value "gender" :checked true}]
       [:label {:for "radio-gender"} "Gender"]]]]
    [:div
     [:ul
      (for [rec recs]
        [:li rec])]]
    ;;[:script {:src "http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"}]
    ;;[:script {:src "/bootstrap/js/bootstrap.min.js"}]
    ]))
