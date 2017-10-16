(ns whelp.view.presentation
  (:require [whelp.view.table :as table]
            [whelp.view.text-field :as text-field]))

(defonce component-atom (atom {:label "Some Label"
                               :value "Some value"}))

(def demo
  {:render (fn []
             [:div
              [table/view]
              [:div {:style {:margin-bottom "20px"}}]
              [text-field/input-demo]]
             )})
