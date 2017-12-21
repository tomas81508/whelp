(ns whelp.view.button
  (:require [whelp.color :as color]
            [whelp.style :as style]
            [whelp.view.paper :refer [paper]]
            [whelp.view.typography :as typography]
            [whelp.view.doc :as doc]))

(defn overlay-span [pressed]
  [:span {:style {:height           (if pressed "100%" "0%")
                  :width            (if pressed "100%" "0%")
                  :transition       "all 4500ms cubic-bezier(0.23, 1, 0.32, 1) 0ms"
                  :position         "absolute"
                  :top              "0px"
                  :left             "0px"
                  :overflow         "hidden"
                  :pointer-events   "none"
                  :z-index          1
                  :border-radius    "inherit"
                  :background-color "rgba(153, 153, 153, 0.72)"}}])

(def button
  {:get-initial-state (fn []
                        {:pressed false})
   :input             [{:name      "element-hovered"
                        :input-key :hovered}]
   :render            (fn [{children        :children
                            dense           :dense
                            disabled        :disabled
                            hovered         :hovered
                            label           :label
                            type            :type
                            view-state      :view-state
                            view-state-atom :view-state-atom}]
                        (let [dense (or dense false)
                              disabled (or disabled false)
                              type (or type :flat)]
                          [paper {:elevation (cond (= type :flat) 0
                                                   (and (= type :raised) (:pressed view-state)) 3
                                                   (and (= type :raised) (not (:pressed view-state))) 1)
                                  :width     "inherit"}
                           [:div (merge {:element-hovered-value true
                                         :style                 {:cursor           "pointer"
                                                                 :user-select      "none"
                                                                 :height           (if dense "32px" "36px")
                                                                 :position         "relative"
                                                                 :padding-left     "16px"
                                                                 :padding-right    "16px"
                                                                 :vertical-align   "middle"
                                                                 :text-transform   "uppercase"
                                                                 :font-weight      "500"
                                                                 :text-align       "center"
                                                                 :min-width        "88px"
                                                                 :border-radius    "inherit"
                                                                 :color            (if disabled color/black-26% color/black-87%)
                                                                 :font-size        (if dense "13px" "14px")
                                                                 :line-height      (if dense "32px" "36px")
                                                                 :transition       "all 450ms cubic-bezier(0.23, 1, 0.32, 1) 0ms"
                                                                 :background-color (if hovered
                                                                                     "rgba(153, 153, 153, 0.12)"
                                                                                     "rgba(0, 0, 0, 0)")}}
                                        (when-not disabled
                                          {:on-mouse-down  (fn [] (swap! view-state-atom assoc :pressed true))
                                           :on-mouse-up    (fn [] (swap! view-state-atom assoc :pressed false))
                                           :on-mouse-enter    (fn [e] (js/console.log e))
                                           :on-mouse-leave (fn [] (swap! view-state-atom assoc :pressed false))}))
                            (overlay-span (:pressed view-state))
                            label
                            children]]))})

(def floating-action-button
  {:get-initial-state (fn [] {:pressed false})
   :input             [{:name      "element-hovered"
                        :input-key :hovered}]
   :render            (fn [{type            :type
                            icon            :icon
                            hovered         :hovered
                            view-state      :view-state
                            view-state-atom :view-state-atom}]
                        [paper {:elevation (cond (:pressed view-state) 3
                                                 :else 1)
                                :width     "inherit"
                                :circle    true}
                         [:div {:element-hovered-value true
                                :style                 {:cursor           "pointer"
                                                        :user-select      "none"
                                                        :height           (if (= type :mini) "40px" "56px")
                                                        :width            (if (= type :mini) "40px" "56px")
                                                        :position         "relative"
                                                        :vertical-align   "middle"
                                                        :text-transform   "uppercase"
                                                        :padding          (if (= type :mini) "8px" "16px")
                                                        :font-weight      "500"
                                                        :text-align       "center"
                                                        :border-radius    "inherit"
                                                        :color            color/black-87%
                                                        :transition       "all 450ms cubic-bezier(0.23, 1, 0.32, 1) 0ms"
                                                        :background-color (if hovered
                                                                            "rgba(153, 153, 153, 0.12)"
                                                                            "rgba(0, 0, 0, 0)")}
                                :on-mouse-down         (fn [] (swap! view-state-atom assoc :pressed true))
                                :on-mouse-up           (fn [] (swap! view-state-atom assoc :pressed false))
                                :on-mouse-leave        (fn [] (swap! view-state-atom assoc :pressed false))}

                          (overlay-span (:pressed view-state))

                          [:div {:style {:background-color "red"
                                         :height           "24px"
                                         :width            "24px"}}
                           icon]]])})

(def button-demo
  {:render (fn [{state-atom :state-atom}]
             [:div
              [doc/showcase-2
               [:div {:style {:padding "5px"}}
                [button {:label "Flat"}]]
               [:div {:style {:padding "5px"}}
                [button {:label    "Disabled"
                         :disabled true}]]
               [:div {:style {:padding "5px"}}
                [button {:label "Dense"
                         :dense true}]]]
              [doc/showcase-2
               [:div {:style {:padding "5px"}}
                [button {:label "Raised"
                         :type  :raised}]]
               [:div {:style {:padding "5px"}}
                [button {:label    "Disabled"
                         :disabled true
                         :type     :raised}]]
               [:div {:style {:padding "5px"}}
                [button {:label "Dense"
                         :dense true
                         :type  :raised}]]]
              [doc/showcase-2
               [:div {:style {:padding "5px"}}
                [floating-action-button {:icon [:div {:style {:width            "24px"
                                                              :height           "24px"
                                                              :background-color "gray"}}]}]]
               [:div {:style {:padding "5px"}}
                [floating-action-button {:icon [:div {:style {:width            "24px"
                                                              :height           "24px"
                                                              :background-color "gray"}}]
                                         :type :mini}]]]])})

