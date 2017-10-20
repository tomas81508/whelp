(ns whelp.view.presentation
  (:require [whelp.view.table :as table]
            [whelp.view.text-field :as text-field]))

(def demo
  {:render (fn [{state-atom :state-atom :as input}]
             [:div {:style {:margin "10px"}}
              [table/view input]
              [:h2 "input"]
              [text-field/input-demo input]]
             )})
