(ns portfolio.core
    (:require [reagent.core :as reagent]))

;; -------------------------
;; Views

(defonce app-state
  (reagent/atom
   {:text "" :color "rgba(50, 50, 255, 0.2)"}))



(defn navbar []
 [:div.container-fluid.darkbg
  [:div
   [:ul
    [:div.mainnav
     [:li [:a {:href "/"} "Home"]]]]]])

(defn paragraph [ratom]
  (when (> (count (:text @ratom)) 0)
   [:div {:style {:background-color (:color @ratom) :width 160}}
    [:p "Hi, "(:text @ratom)]]))

(defn page [ratom]
  (let [guestname (fn [e]
                   (swap! ratom assoc :text e.target.value))]
   [:p "Hello, what is your name?"
    [:p [:input {:type "text" :on-change guestname}]]
    [paragraph ratom]]))


(defn project-card []
  [:div.card.card-1
   [:div.card-title "My Blog"
    [:hr]
    [:div [:img.card-image {:src "https://dummyimage.com/250x250/000/fff.png"}]]]])

(defn footer []
  [:div.footer "2017"])

;; View

(defn portfolio-app [props]
  [:div.navbar (navbar)
   [:section.portfolio-app
     [:div (project-card)]]
   [:footer (footer)]])

;; -------------------------
;; Initialize app

(defn mount-root []
  (reagent/render [portfolio-app] (.getElementById js/document "app")))

(defn init! []
  (mount-root))
