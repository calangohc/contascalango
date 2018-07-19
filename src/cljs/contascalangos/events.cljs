(ns contascalangos.events
  (:require
   [re-frame.core :as re-frame]
   [contascalangos.db :as db]
   [day8.re-frame.tracing :refer-macros [fn-traced defn-traced]]
   ))

(re-frame/reg-event-db
 ::initialize-db
 (fn-traced [_ _]
   db/default-db))

(re-frame/reg-event-db
 ::set-active-panel
 (fn-traced [db [_ active-panel]]
   (assoc db :active-panel active-panel)))

(re-frame/reg-event-db
:adicionar
(fn [db event]
  (let [novo-valor (second event)
        itens (:orcamento db)]
    (assoc db :orcamento (cons novo-valor itens)) )))
