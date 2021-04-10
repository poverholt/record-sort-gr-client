(ns record-sort-gr-client.order-view
  (:require [hiccup.core :refer [html h]]
            [hiccup.page :refer [html5]]))

(defn page []
  (html5
   {:lang "en"}
   
   [:head
    [:meta {:charset "utf-8"}]]

   ;; [:head
   ;;  [:title "Order View"]
   ;;  [:meta {:name :viewport
   ;;          :content "width=device-width, initial-scale=1.0"}]
   ;;  [:link {:hef "/bootstrap/css/bootstrap.min.css"
   ;;          :rel :stylesheet}]]
   
   [:body
    [:form {:method "POST" :action "/sort"}
     [:input {:type :hidden
              :name "_method"
              :value "PUT"}]
     [:div
      [:input {:type "radio" :id "radio-gender" :name "sort-order" :value "gender" :checked true}]
      [:label {:for "radio-gender"} "Gender"]]
     [:div
      [:input {:type "radio" :id "radio-bdate" :name "sort-order" :value "birthdate" :checked false}]
      [:label {:for "radio-bdate"} "Birthdate"]]
     [:div
      [:input {:type "radio" :id "radio-name" :name "sort-order" :value "name" :checked false}]
      [:label {:for "radio-name"} "Name"]]
     [:button {:type "submit"} "OK"]]]))
