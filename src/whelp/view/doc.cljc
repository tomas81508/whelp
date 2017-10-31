(ns whelp.view.doc
  (:require [whelp.color :as color]))

(def module-size-1 "360px")
(def module-size-2 "760px")

(def section
  {:render (fn [{children :children}]
             [:section {:style {:margin-top "60px"}}
              children])})

(def module
  {:render (fn [{children :children
                 style    :style}]
             [:div {:style (merge {:display        "inline-block"
                                   :vertical-align "top"
                                   :width          module-size-1
                                   :margin-right   "40px"
                                   :padding-bottom "20px"}
                                  style)}
              children])})

(def module-1 module)

(def module-2
  {:render (fn [input]
             [module (update input :style merge {:width module-size-2})])})

(def showcase-1
  {:render (fn [{children :children}]
             [module-1 {:style {:height     module-size-1
                                :background color/grey-200}}
              [:div {:style {:height          "100%"
                             :box-sizing      "border-box"
                             :display         "flex"
                             :align-items     "center"
                             :justify-content "center"}}
               children]])})

(def showcase-2
  {:render (fn [{children :children}]
             [module-2 {:style {:height     module-size-2
                                :background color/grey-200}}
              [:div {:style {:height          "100%"
                             :box-sizing      "border-box"
                             :display         "flex"
                             :align-items     "center"
                             :justify-content "center"}}
               children]])})