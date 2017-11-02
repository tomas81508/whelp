(ns whelp.view.paper
  (:require [whelp.color :as color]
            [whelp.style :as style]
            [whelp.view.typography :refer [title]]
            [whelp.view.doc :as doc]))

(def paper
  {:render (fn [{depth :depth
                 children :children}]
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
                            :border-radius       "2px"
                            :height              "100px"
                            :width               "100px"
                            :margin              "20px"
                            :text-align          "center"
                            :display             "inline-block"}}
              children])})

(def paper-demo
  {:render (fn [{state-atom :state-atom}]
             [:div
              [doc/showcase-2
               [:div
                [paper {:depth 1}]
                [paper {:depth 2}]
                [paper {:depth 3}]
                [paper {:depth 4}]
                [paper {:depth 5}]]]])})

