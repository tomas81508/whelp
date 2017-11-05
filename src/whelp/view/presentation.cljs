(ns whelp.view.presentation
  (:require [whelp.color :as color]
            [whelp.view.button :as button]
            [whelp.view.card :as card]
            [whelp.view.paper :as paper]
            [whelp.view.table :as table]
            [whelp.view.text-field :as text-field]
            [whelp.view.navigation-drawer :as navigation-drawer]
            [whelp.view.typography :refer [display-1]]))

;; 64*4 (standard increment for tablet)
(def navigation-drawer-width "256px")

(def demo
  {:render (fn [{state-atom :state-atom :as input}]
             [:div
              [:div {:style {:position     "fixed"
                             :height       "100%"
                             :background   color/white
                             :border-right "1px solid rgba(0,0,0,.14)"}}
               [navigation-drawer/navigation-drawer {:width  navigation-drawer-width
                                                     :height "100%"
                                                     :items  [{:title "Data table"}
                                                              {:title "Input"}
                                                              {:title "Navigation drawer"}]}]]
              [:div {:style {:padding-left navigation-drawer-width
                             :background   color/grey-50}}
               [:div {:style {:padding-left   "24px"
                              :padding-top    "80px"
                              :padding-bottom "100px"}}
                [:div
                 [display-1 {:color (color/get-color nil :primary "A700")}
                  "Data table"]
                 [table/view input]]

                [:div {:style {:padding-top "80px"}}
                 [display-1 {:color (color/get-color nil :primary "A700")}
                  "Input"]
                 [text-field/input-demo input]]

                [:div {:style {:padding-top "80px"}}
                 [display-1 {:color (color/get-color nil :primary "A700")}
                  "Papers"]
                 [paper/paper-demo input]]

                [:div {:style {:padding-top "80px"}}
                 [display-1 {:color (color/get-color nil :primary "A700")}
                  "Button"]
                 [button/button-demo input]]

                [:div {:style {:padding-top "80px"}}
                 [display-1 {:color (color/get-color nil :primary "A700")}
                  "Card"]
                 [card/card-demo input]]

                [:div {:style {:padding-top "80px"}}
                 [display-1 {:color (color/get-color nil :primary "A700")}
                  "Navigation drawer"]
                 [navigation-drawer/navigation-drawer-demo input]]]]])})
