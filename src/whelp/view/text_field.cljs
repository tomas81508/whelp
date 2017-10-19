(ns whelp.view.text-field
  (:require [whelp.style :as style]))

(def input
  {:get-initial-state (fn []
                        {:selected false})
   :input             [{:name      "element-hovered"
                        :input-key :hovered-item}]
   :render            (fn [{hovered-item    :hovered-item
                            view-state      :view-state
                            view-state-atom :view-state-atom
                            placeholder     :placeholder
                            label           :label
                            value           :value
                            width           :width
                            on-change       :on-change
                            on-click        :on-click
                            on-input        :on-input}]
                        [:div {:style {:width       (or width "100%")
                                       :font-family "Roboto, sans-serif"
                                       :background  style/white}}
                         [:div {:style {}}
                          (let [width "200px"]
                            [:div {:style {:font-size        "16px"
                                           :line-height      "24px"
                                           :width            width
                                           :height           "72px"
                                           :display          "inline-block"
                                           :position         "relative"
                                           :background-color "transparent"
                                           :font-family      "Roboto, sans-serif"
                                           :transition       "height 200ms cubic-bezier(0.23, 1, 0.32, 1) 0ms"
                                           :cursor           "auto"}}
                             [:label {:style {:position         "absolute"
                                              :line-height      "22px"
                                              :top              "38px"
                                              :transition       "all 450ms cubic-bezier(0.23, 1, 0.32, 1) 0ms"
                                              :z-index          1
                                              :transform        "scale(0.75) translate(0px, -28px)"
                                              :transform-origin "left top 0px"
                                              :pointer-events   "none"
                                              :user-select      "none"
                                              :color            "rgba(0, 0, 0, 0.3)"}}
                              label]
                             [:input (merge {:type      "text"
                                             :on-change (fn [e] (and on-change
                                                                     (on-change (-> e .-target .-value))))
                                             :on-input  (fn [e] (and on-input
                                                                     (on-input (-> e .-target .-value))))
                                             :on-focus  (fn [e] (swap! view-state-atom assoc :selected true))
                                             :on-blur   (fn [e] (swap! view-state-atom assoc :selected false))
                                             :style     (merge {:padding             "0px"
                                                                :position            "relative"
                                                                :width               "100%"
                                                                :border              "none"
                                                                :outline             "none"
                                                                :background-color    "rgba(0, 0, 0, 0)"
                                                                :cursor              "inherit"
                                                                :font-style          "inherit"
                                                                :font-variant        "inherit"
                                                                :font-weight         "inherit"
                                                                :font-stretch        "inherit"
                                                                :font-size           "inherit"
                                                                :line-height         "inherit"
                                                                :font-family         "inherit"
                                                                :opacity             1
                                                                :tap-highlight-color "rgba(0, 0, 0, 0)"
                                                                :height              "100%"
                                                                :box-sizing          "border-box"
                                                                :margin-top          "14px"}
                                                               (if value
                                                                 {:color "rgba(0, 0, 0, 0.87)"}
                                                                 {:color style/grey-200}))}
                                            (if value
                                              {:value value}
                                              {:placeholder placeholder}))]
                             [:div
                              [:hr {:aria-hidden "true"
                                    :style       {:border-top    "none rgb(224, 224, 224)"
                                                  :border-left   "none rgb(224, 224, 224)"
                                                  :border-right  "none rgb(224, 224, 224)"
                                                  :border-bottom "1px solid rgb(224, 224, 224)"
                                                  :bottom        "8px"
                                                  :box-sizing    "content-box"
                                                  :margin        "0px"
                                                  :position      "absolute"
                                                  :width         "100%"}}]
                              [:hr {:aria-hidden "true"
                                    :style       {:border-top    "none rgb(0, 188, 212)"
                                                  :border-left   "none rgb(0, 188, 212)"
                                                  :border-right  "none rgb(0, 188, 212)"
                                                  :border-bottom (str "2px solid " style/main-color)
                                                  :bottom        "8px"
                                                  :box-sizing    "content-box"
                                                  :margin        "0px"
                                                  :position      "absolute"
                                                  :width         "100%"
                                                  :transform     (if (:selected view-state) "scaleX(1)" "scaleX(0)")
                                                  :transition    "all 450ms cubic-bezier(0.23, 1, 0.32, 1) 0ms"}}]]])]])})

(defonce input-state-atom (atom {:placeholder "Placeholder text"
                                 :label       "Some label"
                                 :value       "AA"}))

(def input-demo
  {:render (fn []
             [:div {:style {:margin           "25px"
                            :background-color style/grey-200}}
              [:div {:style {:width   "250px"
                             :margin  "25px"
                             :display "inline-block"}}
               [input {:label       "Label"
                       :placeholder "Customize the label"
                       :on-input    (fn [value]
                                      (swap! input-state-atom assoc :label value))}]
               [input {:label       "Initial value"
                       :placeholder "Customize the initial value"
                       :on-input    (fn [value]
                                      (swap! input-state-atom assoc :placeholder value))}]]
              [:div {:style {:width          "250px"
                             :margin         "25px"
                             :display        "inline-block"
                             :vertical-align "top"}}
               [input {:value    (:value @input-state-atom)
                       :label    (:label @input-state-atom)
                       :on-input (fn [value] (swap! input-state-atom assoc :value value))}]]])})


