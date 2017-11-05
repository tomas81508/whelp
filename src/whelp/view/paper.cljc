(ns whelp.view.paper
  (:require [whelp.color :as color]
            [whelp.style :as style]
            [whelp.view.typography :refer [title]]
            [whelp.view.doc :as doc]))

(def paper
  {:render (fn [{depth    :depth
                 rounded  :rounded
                 circle   :circle
                 width    :width
                 height   :height
                 margin   :margin
                 children :children}]
             (let [rounded (or rounded true)
                   circle (or circle false)]
               [:div {:style {:color               "rgba(0, 0, 0, 0.87)"
                              :background-color    "rgb(255, 255, 255)"
                              :transition          "all 450ms cubic-bezier(0.23, 1, 0.32, 1) 0ms"
                              :box-sizing          "border-box"
                              :font-family         "Roboto, sans-serif"
                              :tap-highlight-color "rgba(0, 0, 0, 0)"
                              :box-shadow          (condp = depth
                                                     0 ""
                                                     1 "rgba(0, 0, 0, 0.12) 0px 1px 6px, rgba(0, 0, 0, 0.12) 0px 1px 4px"
                                                     2 "rgba(0, 0, 0, 0.16) 0px 3px 10px, rgba(0, 0, 0, 0.23) 0px 3px 10px"
                                                     3 "rgba(0, 0, 0, 0.19) 0px 10px 30px, rgba(0, 0, 0, 0.23) 0px 6px 10px"
                                                     4 "rgba(0, 0, 0, 0.25) 0px 14px 45px, rgba(0, 0, 0, 0.22) 0px 10px 18px"
                                                     5 "rgba(0, 0, 0, 0.3) 0px 19px 60px, rgba(0, 0, 0, 0.22) 0px 15px 20px")
                              :border-radius       (cond circle
                                                         "50%"

                                                         rounded
                                                         "2px"

                                                         :else
                                                         "0px")
                              :height              (or height "auto")
                              :width               (or width "100%")
                              :margin              (or margin "0px")
                              :display             "inline-block"}}
                children]))})

(def paper-demo
  {:render (fn [{state-atom :state-atom}]
             (let [size-parameters {:width  "100px"
                                    :height "100px"
                                    :margin "20px"}]
               [:div
                [doc/showcase-2
                 ;[title "Paper rounded corners"]
                 ;[:div ":depth 0-5"]
                 [:div
                  [paper (merge {:depth 0} size-parameters)]
                  [paper (merge {:depth 1} size-parameters)]
                  [paper (merge {:depth 2} size-parameters)]
                  [paper (merge {:depth 3} size-parameters)]
                  [paper (merge {:depth 4} size-parameters)]
                  [paper (merge {:depth 5} size-parameters)]]
                 ;[title "Paper non-rounded corners"]
                 ;[:div ":depth 0-5 :rounded false"]
                 [:div
                  [paper (merge {:depth 0 :rounded false} size-parameters)]
                  [paper (merge {:depth 1 :rounded false} size-parameters)]
                  [paper (merge {:depth 2 :rounded false} size-parameters)]
                  [paper (merge {:depth 3 :rounded false} size-parameters)]
                  [paper (merge {:depth 4 :rounded false} size-parameters)]
                  [paper (merge {:depth 5 :rounded false} size-parameters)]]
                 [:div
                  [paper (merge {:depth 0 :circle true} size-parameters)]
                  [paper (merge {:depth 1 :circle true} size-parameters)]
                  [paper (merge {:depth 2 :circle true} size-parameters)]
                  [paper (merge {:depth 3 :circle true} size-parameters)]
                  [paper (merge {:depth 4 :circle true} size-parameters)]
                  [paper (merge {:depth 5 :circle true} size-parameters)]]]]))})

