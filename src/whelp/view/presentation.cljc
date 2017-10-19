(ns whelp.view.presentation
  (:require [whelp.view.table :as table]
            [whelp.view.text-field :as text-field]))

(def demo
  {:render (fn [{state-atom :state-atom :as input}]
             [:div
              [table/view input]
              [:div {:style {:margin-bottom "20px"}}]
              [text-field/input-demo input]]
             )})
