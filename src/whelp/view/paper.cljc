(ns whelp.view.paper
  (:require [whelp.color :as color]
            [whelp.style :as style]
            [whelp.view.typography :refer [title]]
            [whelp.view.doc :as doc]))

(def paper
  {:name   "paper"
   :render (fn [{elevation :elevation
                 rounded   :rounded
                 circle    :circle
                 width     :width
                 height    :height
                 margin    :margin
                 children  :children}]
             (let [rounded (or rounded true)
                   circle (or circle false)]
               [:div {:style (merge {:color               "rgba(0, 0, 0, 0.87)"
                                     :background-color    "rgb(255, 255, 255)"
                                     :transition          "all 450ms cubic-bezier(0.23, 1, 0.32, 1) 0ms"
                                     :box-sizing          "border-box"
                                     :font-family         "Roboto, sans-serif"
                                     :tap-highlight-color "rgba(0, 0, 0, 0)"
                                     :border-radius       (cond circle
                                                                "50%"

                                                                rounded
                                                                "2px"

                                                                :else
                                                                "0px")
                                     :height              (or height "auto")
                                     :width               (or width "100%")
                                     :margin              (or margin "0px")
                                     :display             "inline-block"}
                                    {:box-shadow (style/box-shadow elevation)})}
                children]))})

(def paper-demo
  {:render (fn [{state-atom :state-atom}]
             (let [size-parameters {:width  "100px"
                                    :height "100px"
                                    :margin "20px"}]
               [:div
                [doc/showcase-2
                 ;[title "Paper rounded corners"]
                 ;[:div ":elevation 0-5"]
                 [:div
                  [paper (merge {:elevation 0} size-parameters)]
                  [paper (merge {:elevation 1} size-parameters)]
                  [paper (merge {:elevation 2} size-parameters)]
                  [paper (merge {:elevation 3} size-parameters)]
                  [paper (merge {:elevation 4} size-parameters)]
                  [paper (merge {:elevation 5} size-parameters)]]
                 ;[title "Paper non-rounded corners"]
                 ;[:div ":elevation 0-5 :rounded false"]
                 [:div
                  [paper (merge {:elevation 0 :rounded false} size-parameters)]
                  [paper (merge {:elevation 1 :rounded false} size-parameters)]
                  [paper (merge {:elevation 2 :rounded false} size-parameters)]
                  [paper (merge {:elevation 3 :rounded false} size-parameters)]
                  [paper (merge {:elevation 4 :rounded false} size-parameters)]
                  [paper (merge {:elevation 5 :rounded false} size-parameters)]]
                 [:div
                  [paper (merge {:elevation 0 :circle true} size-parameters)]
                  [paper (merge {:elevation 1 :circle true} size-parameters)]
                  [paper (merge {:elevation 2 :circle true} size-parameters)]
                  [paper (merge {:elevation 3 :circle true} size-parameters)]
                  [paper (merge {:elevation 4 :circle true} size-parameters)]
                  [paper (merge {:elevation 5 :circle true} size-parameters)]]]]))})

