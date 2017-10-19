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
                            error-text      :error-text
                            value           :value
                            helper-text     :helper-text
                            width           :width
                            on-change       :on-change
                            on-click        :on-click
                            on-input        :on-input}]
                        (let [width (or width "200px")
                              value? (and value (not= value ""))
                              error? (and error-text (not= error-text ""))
                              selected? (:selected view-state)]
                          [:div {:style {:font-size        "16px"
                                         :height           "82px"
                                         :width            width
                                         :display          "inline-block"
                                         :position         "relative"
                                         :background-color "transparent"
                                         :font-family      "Roboto, sans-serif"
                                         :transition       "height 200ms cubic-bezier(0.23, 1, 0.32, 1) 0ms"
                                         :cursor           "auto"}}
                           [:label {:style (merge {:position       "absolute"
                                                   :display        "block"
                                                   :pointer-events "none"
                                                   :user-select    "none"
                                                   :transition     "all 450ms cubic-bezier(0.23, 1, 0.32, 1) 0ms"}
                                                  (cond error?
                                                        {:color (style/get-color nil :red "A400")}

                                                        selected?
                                                        {:color (style/get-color nil :primary "A700")}

                                                        :else
                                                        {:color style/black-54%})
                                                  (if (or value? selected?)
                                                    {:font-size  "12px"
                                                     :margin-top "16px"}
                                                    {:font-size  "16px"
                                                     :margin-top "36px"}))}
                            label]
                           [:div {:element-hovered-value true
                                  :style                 {:margin-top     "36px"
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
                                                               :background-color    "transparent"
                                                               :cursor              "inherit"
                                                               :font-style          "inherit"
                                                               :font-variant        "inherit"
                                                               :font-weight         "inherit"
                                                               :font-stretch        "inherit"
                                                               :font-size           "inherit"
                                                               :font-family         "inherit"
                                                               :opacity             1
                                                               :tap-highlight-color "transparent"
                                                               :box-sizing          "border-box"
                                                               :caret-color         (style/get-color nil :primary "A700")}
                                                              (if value?
                                                                {:color style/black-87%}
                                                                {:color style/black-42%}))}
                                           (cond value?
                                                 {:value value}

                                                 selected?
                                                 {:placeholder placeholder}

                                                 :else
                                                 nil))]
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
                                                         {:border-color        style/black-87%
                                                          :border-bottom-width "2px"}
                                                         {:border-color        style/black-42%
                                                          :border-bottom-width "1px"}))}]
                             [:hr {:aria-hidden "true"
                                   :style       {:border-top          "none"
                                                 :border-left         "none"
                                                 :border-right        "none"
                                                 :border-bottom-style "solid"
                                                 :border-bottom-width "2px"
                                                 :border-color        (style/get-color nil :primary "500")
                                                 :bottom              "0px"
                                                 :box-sizing          "content-box"
                                                 :margin              "0px"
                                                 :position            "absolute"
                                                 :width               "100%"
                                                 :transform           (if (:selected view-state) "scaleX(1)" "scaleX(0)")
                                                 :transition          "all 450ms cubic-bezier(0.23, 1, 0.32, 1) 0ms"}}]]]
                           (if error?
                             [:div {:style {:margin-top "8px"
                                            :font-size  "12px"
                                            :color      (style/get-color nil :red "A400")}}
                              (str "Error: " error-text)]
                             [:div {:style {:margin-top "8px"
                                            :font-size  "12px"
                                            :color      style/black-54%}}
                              helper-text])]))})

(def input-demo
  {:render (fn [{state-atom :state-atom}]
             [:div {:style {:margin           "25px"
                            :background-color style/white}}
              [:div {:style {:width   "250px"
                             :margin  "25px"
                             :display "inline-block"}}
               [input {:label    ":label"
                       :value    (get-in @state-atom [:components :text-field :label])
                       :on-input (fn [value]
                                   (swap! state-atom assoc-in [:components :text-field :label] value))}]
               [input {:label    ":placeholder"
                       :value    (get-in @state-atom [:components :text-field :placeholder])
                       :on-input (fn [value]
                                   (swap! state-atom assoc-in [:components :text-field :placeholder] value))}]
               [input {:label    ":helper-text"
                       :value    (get-in @state-atom [:components :text-field :helper-text])
                       :on-input (fn [value]
                                   (swap! state-atom assoc-in [:components :text-field :helper-text] value))}]
               [input {:label      ":error-text"
                       :value      (get-in @state-atom [:components :text-field :error-text])
                       :error-text (get-in @state-atom [:components :text-field :error-text])
                       :on-input   (fn [value]
                                     (swap! state-atom assoc-in [:components :text-field :error-text] value))}]]
              [:div {:style {:width          "250px"
                             :margin         "25px"
                             :display        "inline-block"
                             :vertical-align "top"}}
               [input {:width       "300px"
                       :value       (get-in @state-atom [:components :text-field :value])
                       :label       (get-in @state-atom [:components :text-field :label])
                       :placeholder (get-in @state-atom [:components :text-field :placeholder])
                       :error-text  (get-in @state-atom [:components :text-field :error-text])
                       :helper-text (get-in @state-atom [:components :text-field :helper-text])
                       :on-input    (fn [value] (swap! state-atom assoc-in [:components :text-field :value] value))}]
               [input {:width       "150px"
                       :value       (get-in @state-atom [:components :text-field :value])
                       :label       (get-in @state-atom [:components :text-field :label])
                       :placeholder (get-in @state-atom [:components :text-field :placeholder])
                       :error-text  (get-in @state-atom [:components :text-field :error-text])
                       :helper-text (get-in @state-atom [:components :text-field :helper-text])
                       :on-input    (fn [value] (swap! state-atom assoc-in [:components :text-field :value] value))}]]])})


