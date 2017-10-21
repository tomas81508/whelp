(ns whelp.view.typography
  (:require [whelp.color :as color]
            [whelp.style :as style]))

(def display-1
  {:render (fn [{children :children
                 color    :color}]
             [:h1 {:style {:font-family "Roboto, sans-serif"
                           :font-size   "34px"
                           :font-weight 400
                           :color       color
                           :margin      "0 0 30px 0"}}
              children])})