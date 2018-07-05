(ns contascalangos.views
  (:require
   [re-frame.core :as re-frame]
   [contascalangos.subs :as subs]
   [reagent.core :as r]
   ))


;; home

(defn home-panel []
  (let [name (re-frame/subscribe [::subs/name])
        orcamento (re-frame/subscribe [::subs/orc]) ]
    (r/with-let [nova (r/atom  0)]
      [:div
       [:h2 (str @name)]

       [:div
        [:table [:tbody
                 [:tr [:td "Receita"] [:td "Gasta"]]

                 (for [item @orcamento]
                   (if (< 0 item)
                     [:tr
                      [:td  item] [:td] ]
                     [:tr
                      [:td]
                      [:td
                       [:span {:class "negative"} item]]]))]
         ]
        ]


       [:div [:h3 "Total : " (apply + @orcamento)]]

       [:div
        [:input {:type "text"
                 :value @nova
                 :on-change #(reset! nova (-> % .-target .-value))
                 }]

        [:button {:on-click #(re-frame/dispatch [:adicionar (int @nova)])} "Adicionar" ]

        ]


       [:div
        [:a {:href "#/about"}
         "go to About Page"]]

       ])))


;; about

(defn about-panel []
  [:div
   [:h1 "This is the About Page."]

   [:div
    [:a {:href "#/"}
     "go to Home Page"]]])


;; main

(defn- panels [panel-name]
  (case panel-name
    :home-panel [home-panel]
    :about-panel [about-panel]
    [:div]))

(defn show-panel [panel-name]
  [panels panel-name])

(defn main-panel []
  (let [active-panel (re-frame/subscribe [::subs/active-panel])]
    [show-panel @active-panel]))
