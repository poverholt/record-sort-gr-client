(ns record-sort-gr-client.main-view
  (:require [hiccup.core :refer [html h]]
            [hiccup.page :refer [html5]]))

(defn page [recs error-strings]
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
    (when error-strings
      [:div
       [:h1 "Errors"]
       (into [:ul] (for [s error-strings]
                     [:li s]))])
    
    [:h1 "Record Sorting"]

    [:div
     [:form {:method "GET" :action "/add"}
      [:button {:type "submit"} "Add"]]
     [:form {:method "POST" :action "/"}
      [:input {:type :hidden
               :name "_method"
               :value "DELETE"}]
      [:button {:type "submit"} "Reset"]]
     [:form {:method "GET" :action "/sort"}
      [:button {:type "submit"} "Sort"]]]

    [:div
     [:p (str "There are " (count recs) " records.")]
     [:ul
      (for [rec recs]
        [:li rec])]]
    
    ;;[:script {:src "http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"}]
    ;;[:script {:src "/bootstrap/js/bootstrap.min.js"}]
    ]))
