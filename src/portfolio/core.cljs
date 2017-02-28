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

(defn home-page []
  [:div [:h2 "Welcome tto Reagent"]])

;; Page
(defn paragraph [ratom]
  (println (:color @ratom))
  (when (> (count (:text @ratom)) 0)
   [:div {:style {:background-color (:color @ratom) :width 160}}
    [:p "Hi,  "(:text @ratom)]]))


(defn page [ratom]
  (let [guestname (fn [e]
                   (swap! ratom assoc :text e.target.value))]
   [:p "Hello, what is your name?"
    [:p [:input {:type "text" :on-change guestname}]]
    [paragraph ratom]]))


;; -------------------------
;; Initialize app

(defn mount-root []
  (reagent/render [page app-state] (.getElementById js/document "app")))

(defn init! []
  (mount-root))
