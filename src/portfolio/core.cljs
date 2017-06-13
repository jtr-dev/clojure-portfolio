(ns portfolio.core
    (:require [reagent.core :as reagent]
              [secretary.core :as secretary]
              [goog.events :as events]
              [goog.history.EventType :as EventType]
              [reagent.core :as reagent])
    (:require-macros [secretary.core :refer [defroute]])
    (:import goog.History))


(defn hook-browser-navigation! []
      (doto (History.)
        (events/listen
         EventType/NAVIGATE
         (fn [event]
           (secretary/dispatch! (.-token event))))
        (.setEnabled true)))

;; -------------------------
;; Views
(defonce app-state
  (reagent/atom {}))

(defn app-routes []
  (secretary/set-config! :prefix "#"))

(defroute "/" []
      (swap! app-state assoc :page :home))

(defroute "/about" []
      (swap! app-state assoc :page :about))

(defroute "/portfolio" []
      (swap! app-state assoc :page :portfolio))

(defroute "/contact" []
      (swap! app-state assoc :page :contact))

(hook-browser-navigation!)

(defmulti current-page #(@app-state :page))
(defmethod current-page :home []
  [:div "Home page"])

(defmethod current-page :about []
  [:div "About page"
   [:button "CLICK ME"]])

(defmethod current-page :portfolio []
 (repeat 6
  [:div.a
   [:div.b]
   [:div.c
    [:div.c--inner
     [:h3 "Portfolio Thing"]
     [:p.subtitle "Catchy subtitle"]
     [:a.cta {:href "#"} "GO"]]]]))

(defn current-year [] (.format (java.text.SimpleDateFormat. "yyyy") (new java.util.Date)))
; (str (current-year) " asdasd")

(defn footer []
 [:div {:style {:background-color "#20202D"}}
      "2017"  "/" [:a {:href "https://github.com"} "Tyler Roberts"]
      "/"
      [:a  {:href "https://github.com"} "Reid Quisenberry"]])


(defn port-data []
 [{:title "My Blog" :url "https"}
  {:title "My Site" :url "https"}])

(map #([:title]) port-data)

(defmethod current-page :contact [])
  ; (map #([:li :title] port-data)))
  ; [:table
  ;  (for [i (range 10)]
  ;    [:tr (for [j (range 3)]
  ;           [:td (str "Row " (+ 1 i) ", Col " (+ 1 j))])])]))

; (defn lister [items]
;   [:ul
;    (for [item items]
;      ^{:key item} [:li "Item " item])])
;
; (defn lister-user []
;   [:div
;    "Here is a list:"
;    [lister (range 3)]])
;

(defn navbar []
 [:div#menu
  [:nav
   [:ul
    [:li
     [:a {:href "#/"}
      [:span "Home"]]]
    [:li
     [:a {:href "#/about"}
      [:span "About"]]]
    [:li
     [:a {:href "#/portfolio"}
      [:span "Portfolio"]]]
    [:li
     [:a {:href "#/contact"}
      [:span "Contact"]]]]]])


;; View
(defn portfolio-app [props]
 [:div.container
  [:div (navbar)]
  [:hr]
  (current-page)
  [:hr]
  [:footer.footer (footer)]])

;; -------------------------
;; Initialize app

(defn mount-root []
  (app-routes)
  (reagent/render [portfolio-app] (.getElementById js/document "app")))

(defn init! []
  (mount-root))
