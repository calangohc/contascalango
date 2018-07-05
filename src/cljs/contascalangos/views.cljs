(ns contascalangos.views
  (:require
   [re-frame.core :as re-frame]
   [contascalangos.subs :as subs]
   [reagent.core :as r]
   ))


;; home

(defn home-panel []
  (let [name (re-frame/subscribe [::subs/name]) 
        orcamento (re-frame/subscribe [::subs/orc])
        nova (re-frame/subscribe [::subs/tmp-nova])]
    [:div
     [:h1 (str "Olá Calangos do " @name)]

     [:div 
       [:ul
         (for [item @orcamento] [:li item])
       ]
     ]

     [:div (apply + @orcamento)]

     [:div
        [:input {:type "text"
         :value @nova
         :on-change #(re-frame/dispatch [:tmp-nova (-> % .-target .-value)] )
          }]

        [:button {:on-click #(re-frame/dispatch [:adicionar ])} "Adicionar" ]

     ]


     [:div
      [:a {:href "#/about"}
       "go to About Page"]]

     ]))


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
