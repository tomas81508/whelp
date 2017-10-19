(ns whelp.view.text-field
  (:require [whelp.style :as style]))

(def input
  {:get-initial-state (fn []
                        {:selected false})
   :input             [{:name      "element-hovered"
                        :input-key :hovered}]
   :render            (fn [{hovered         :hovered
                            view-state      :view-state
                            view-state-atom :view-state-atom
                            placeholder     :placeholder
                            label           :label
                            value           :value
                            helper-text     :helper-text
                            width           :width
                            on-change       :on-change
                            on-click        :on-click
                            on-input        :on-input}]
                        [:div {:style {:width       (or width "100%")
                                       :font-family "Roboto, sans-serif"}}
                         [:div {:style {}}
                          (let [width "200px"]
                            [:div {:style {:font-size        "16px"
                                           :width            width
                                           :display          "inline-block"
                                           :position         "relative"
                                           :background-color "transparent"
                                           :font-family      "Roboto, sans-serif"
                                           :transition       "height 200ms cubic-bezier(0.23, 1, 0.32, 1) 0ms"
                                           :cursor           "auto"}}
                             [:label {:style {:position       "relative"
                                              :display        "block"
                                              :font-size      "12px"
                                              :margin-top     "16px"
                                              :pointer-events "none"
                                              :user-select    "none"
                                              :color          (if (:selected view-state) (style/get-color nil :primary "A700") style/black-54%)}}
                              label]
                             [:div {:element-hovered-value true
                                    :style                 {:margin-top     "8px"
                                                            :padding-bottom "8px"
                                                            :position       "relative"}}
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
                                                                 :font-family         "inherit"
                                                                 :opacity             1
                                                                 :tap-highlight-color "rgba(0, 0, 0, 0)"
                                                                 :box-sizing          "border-box"}
                                                                (if value
                                                                  {:color "rgba(0, 0, 0, 0.87)"}
                                                                  {:color style/grey-200}))}
                                             (if value
                                               {:value value}
                                               {:placeholder placeholder}))]
                              [:div
                               [:hr {:aria-hidden "true"
                                     :style       (merge {:border-top          "none"
                                                          :border-left         "none"
                                                          :border-right        "none"
                                                          :border-bottom-style "solid"
                                                          :bottom              "0px"
                                                          :box-sizing          "content-box"
                                                          :margin              "0px"
                                                          :position            "absolute"
                                                          :width               "100%"}
                                                         (if hovered
                                                           {:border-color        "rgba(0, 0, 0, 0.87)"
                                                            :border-bottom-width "2px"}
                                                           {:border-color        "rgb(224, 224, 224)"
                                                            :border-bottom-width "1px"}))}]
                               [:hr {:aria-hidden "true"
                                     :style       {:border-top    "none rgb(0, 188, 212)"
                                                   :border-left   "none rgb(0, 188, 212)"
                                                   :border-right  "none rgb(0, 188, 212)"
                                                   :border-bottom (str "2px solid " (style/get-color nil :primary "500"))
                                                   :bottom        "0px"
                                                   :box-sizing    "content-box"
                                                   :margin        "0px"
                                                   :position      "absolute"
                                                   :width         "100%"
                                                   :transform     (if (:selected view-state) "scaleX(1)" "scaleX(0)")
                                                   :transition    "all 450ms cubic-bezier(0.23, 1, 0.32, 1) 0ms"}}]]]
                             [:div {:style {:margin-top "8px"
                                            :font-size  "12px"
                                            :color      style/black-54%}}
                              helper-text]])]])})

(def input-demo
  {:render (fn [{state-atom :state-atom}]
             [:div {:style {:margin           "25px"
                            :background-color style/grey-200}}
              [:div {:style {:width   "250px"
                             :margin  "25px"
                             :display "inline-block"}}
               [input {:label       "Label"
                       :placeholder "Customize the label"
                       :on-input    (fn [value]
                                      (swap! state-atom assoc-in [:components :text-field :label] value))
                       :value       (get-in @state-atom [:components :text-field :label])}]
               [input {:label       "Placeholder"
                       :placeholder "Customize the placeholder"
                       :on-input    (fn [value]
                                      (swap! state-atom assoc-in [:components :text-field :placeholder] value))
                       :value       (get-in @state-atom [:components :text-field :placeholder])}]
               [input {:label       "Helper text"
                       :placeholder "Customize the helper text"
                       :on-input    (fn [value]
                                      (swap! state-atom assoc-in [:components :text-field :helper-text] value))
                       :value       (get-in @state-atom [:components :text-field :helper-text])}]]
              [:div {:style {:width          "250px"
                             :margin         "25px"
                             :display        "inline-block"
                             :vertical-align "top"}}
               [input {:value       (get-in @state-atom [:components :text-field :value])
                       :label       (get-in @state-atom [:components :text-field :label])
                       :placeholder (get-in @state-atom [:components :text-field :placeholder])
                       :on-input    (fn [value] (swap! state-atom assoc-in [:components :text-field :value] value))
                       :helper-text (get-in @state-atom [:components :text-field :helper-text])}]]])})


