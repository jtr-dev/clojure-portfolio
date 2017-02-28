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


(defn navbar []
  [:div.container-fluid.darkbg
   [:div
   [:ul
    [:div.mainnav
     [:li "Hello"]]]]])

<<<<<<< HEAD

;; Page
=======
(defn navbar []
   [:h2 "HELLO"])
>>>>>>> 5cf759585c4d7171d11b246d7fcf0591e876539f

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

<<<<<<< HEAD
(defn portfolio-app [props]
  [:div (navbar)
   [:section#portfolio-app
     [:h1 "Hello"]
     (page app-state)]])
=======
(defn project-card []
  [:div.site-title "Site Title"])


(defn portfolio-app [props]
  [:div#header (navbar)
   [:section.your-name
     [:h4 "Type here: "]
     (page app-state)]
   [:section.portfolio-app
     [:h4 "My Blog"]
     [:div.card.card-1 (project-card)]]
   [:div "Footer"]])
>>>>>>> 5cf759585c4d7171d11b246d7fcf0591e876539f

;; -------------------------
;; Initialize app

(defn mount-root []
  (reagent/render [portfolio-app] (.getElementById js/document "app")))

(defn init! []
  (mount-root))
