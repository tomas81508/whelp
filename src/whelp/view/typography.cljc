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

(def title
  {:render (fn [{children :children}]
             [:h1 {:style {:font-family "Roboto, sans-serif"
                           :color       "#212121"
                           :font-size   "20px"
                           :font-weight "400"
                           :line-height "32px"
                           :margin      "0 0 20px 0"
                           :max-width   "940px"}}
              children])})

(def headline
  {:render (fn [{children :children}]
             [:div {:style {:font-family "Roboto, sans-serif"
                            :color       "#212121"
                            :font-size   "24px"
                            :font-weight "normal"
                            :line-height "32px"
                            :margin      "0 0 0 0"
                            :max-width   "940px"}}
              children])})

(def body-1
  {:render (fn [{children :children}]
             [:div {:style {:font-family "Roboto, sans-serif"
                            :color       "#212121"
                            :font-size   "14px"
                            :font-weight "normal"
                            :margin      "0 0 0 0"
                            :max-width   "940px"}}
              children])})