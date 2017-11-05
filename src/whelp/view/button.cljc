(ns whelp.view.button
  (:require [whelp.color :as color]
            [whelp.style :as style]
            [whelp.view.paper :refer [paper]]
            [whelp.view.typography :as typography]
            [whelp.view.doc :as doc]))

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
                            type :type
                            view-state      :view-state
                            view-state-atom :view-state-atom}]
                        (let [dense (or dense false)
                              disabled (or disabled false)
                              type (or type :flat)]
                          [paper {:depth (cond (= type :flat) 0
                                               (and (= type :raised) (:pressed view-state)) 3
                                               (and (= type :raised) (not (:pressed view-state))) 1)
                                  :width "inherit"}
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
                                                                 :min-width        "88px"
                                                                 :text-align       "center"
                                                                 :border-radius    "inherit"
                                                                 :color            (if disabled color/black-26% color/black-87%)
                                                                 :font-size        (if dense "13px" "14px")
                                                                 :line-height      (if dense "32px" "36px")
                                                                 :background-color (if hovered
                                                                                     "rgba(153, 153, 153, 0.12)"
                                                                                     "rgba(0, 0, 0, 0)")}}
                                        (when-not disabled
                                          {:on-mouse-down  (fn [] (swap! view-state-atom assoc :pressed true))
                                           :on-mouse-up    (fn [] (swap! view-state-atom assoc :pressed false))
                                           :on-mouse-leave (fn [] (swap! view-state-atom assoc :pressed false))}))
                            (when (:pressed view-state)
                              [:span {:style {:height           "100%"
                                              :width            "100%"
                                              :position         "absolute"
                                              :top              "0px"
                                              :left             "0px"
                                              :overflow         "hidden"
                                              :pointer-events   "none"
                                              :z-index          1
                                              :border-radius    "inherit"
                                              :background-color "rgba(153, 153, 153, 0.72)"}}])
                            label
                            children]]))})

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
                         :type :raised}]]
               [:div {:style {:padding "5px"}}
                [button {:label    "Disabled"
                         :disabled true
                         :type :raised}]]
               [:div {:style {:padding "5px"}}
                [button {:label "Dense"
                         :dense true
                         :type  :raised}]]]])})

