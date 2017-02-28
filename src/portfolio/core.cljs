(ns portfolio.core
    (:require [reagent.core :as reagent]))

;; -------------------------
;; Views

; (defonce site-name
;   (reagent/atom
;    {:text ""}))

(defonce app-state
  (reagent/atom
   {:text "" :color "rgba(50, 50, 255, 0.2)"}))

;; Page


(defn navbar []
   [:nav "HELLO"])

(defn footer []
   [:div.footer "2017"])

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
  [:div.site-title "My Blog"])


(defn portfolio-app [props]
  [:div#header (navbar)
   [:section.portfolio-app
     [:div.card.card-1 (project-card)]]
   [:footer (footer)]])

;; -------------------------
;; Initialize app

(defn mount-root []
  (reagent/render [portfolio-app] (.getElementById js/document "app")))

(defn init! []
  (mount-root))
