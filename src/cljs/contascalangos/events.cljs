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
(fn [coeffects event]  
  (let [db (:db coeffects)
  	    novo-valor (:tmp-nova db)
  	    itens (:orcamento db)]
  	    {:db (assoc db :orcamento (cons novo-valor itens))}
  	)
 ))

(re-frame/reg-event-db
:tmp-nova
(fn [coeffects event]
  (let [db (:db coeffects)]
     {:db (assoc db :tmp-nova (second event))}
  	    )) ) 