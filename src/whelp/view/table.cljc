(ns whelp.view.table
  (:require [whelp.color :as color]
            [whelp.style :as style]))


(def th-style {:padding-left "24px"})

(def td-style {:padding-left "24px"})

(def data [["Froyo" "159" "6.0" "24" "4.0" "87" "14%" "1%"]
           ["KitKat" "518" "26.0" "65" "7.0" "54" "12%" "6%"]
           ["Cupcake" "305" "3.7" "67" "4.3" "413" "3%" "8%"]
           ["Honeycomb" "408" "3.2" "87" "6.5" "562" "0%" "45%"]])


(def view
  {:get-initial-state (fn []
                        {:selected-items #{}})
   :input             [{:name      "element-hovered"
                        :input-key :hovered-item}]
   :render            (fn [{hovered-item :hovered-item
                            view-state          :view-state}]
                        [:div {:style {:width       "100%"
                                       :font-family "Roboto, sans-serif"
                                       :background  color/grey-200}}
                         [:div {:style (merge {:margin-left   "72px"
                                               :margin-top    "72px"
                                               :margin-bottom "72px"
                                               :margin-right  "72px"
                                               :border-radius "2px"
                                               :display       :inline-block
                                               :background    color/white}
                                              style/z-elevation-2dp)}
                          [:div {:style {:height  "64px"
                                         :padding "16px 0 0 24px"}}
                           [:span {:style {:font-size   "24px"
                                           :color       color/black-87%
                                           :display     "block"
                                           :line-height "36px"}}
                            "Items"]]
                          [:table {:style {:border-collapse :collapse}}
                           [:thead {:style {:font-size   "12px"
                                            :color       color/black-54%
                                            :font-weight "500"}}
                            [:tr {:style {:border-bottom (str "1px solid " color/grey-300)
                                          :height        "56px"}}
                             [:th {:style th-style} [:input {:style {:margin "0"} :type "checkbox"}]]
                             [:th {:style th-style} "Dessert (100g serving)"]
                             [:th {:style (merge th-style
                                                 {:text-align   "right"
                                                  :padding-left "56px"})} "Calories"]
                             [:th {:style (merge th-style
                                                 {:text-align   "right"
                                                  :padding-left "56px"})} "Fat (g)"]
                             [:th {:style (merge th-style
                                                 {:text-align   "right"
                                                  :padding-left "56px"})} "Carbs (g)"]
                             [:th {:style (merge th-style
                                                 {:text-align   "right"
                                                  :padding-left "56px"})} "Protein (g)"]
                             [:th {:style (merge th-style
                                                 {:text-align   "right"
                                                  :padding-left "56px"})} "Sodium (mg)"]
                             [:th {:style (merge th-style
                                                 {:text-align   "right"
                                                  :padding-left "56px"})} "Calcium (%)"]
                             [:th {:style (merge th-style
                                                 {:padding-right "24px"
                                                  :padding-left  "56px"
                                                  :text-align    "right"})} "Iron (%)"]]]
                           [:tbody {:style {:font-size "13px"
                                            :color     color/black-87%}}
                            (map-indexed (fn [index product]
                                           [:tr {:element-hovered-value product
                                                 :style                 (merge {:height "48px"}
                                                                               (when (not= (inc index) (count data))
                                                                                 {:border-bottom (str "1px solid " color/grey-300)})
                                                                               (when (contains? (:selected-items view-state) product)
                                                                                 {:background color/grey-100})
                                                                               (when (= hovered-item product)
                                                                                 {:background color/grey-200}))}
                                            [:td {:style td-style} [:input {:style     {:margin "0"}
                                                                            :type      "checkbox"
                                                                            :checked   (contains? (:selected-items view-state) product)
                                                                            :on-change [:item-select-change {:item product}]}]]
                                            [:td {:style td-style} (nth product 0)]
                                            [:td {:style (merge td-style
                                                                {:text-align "right"})} (nth product 1)]
                                            [:td {:style (merge td-style
                                                                {:text-align "right"})} (nth product 2)]
                                            [:td {:style (merge td-style
                                                                {:text-align "right"})} (nth product 3)]
                                            [:td {:style (merge td-style
                                                                {:text-align "right"})} (nth product 4)]
                                            [:td {:style (merge td-style
                                                                {:text-align "right"})} (nth product 5)]
                                            [:td {:style (merge td-style
                                                                {:text-align "right"})} (nth product 6)]
                                            [:td {:style (merge td-style
                                                                {:padding-right "24px"
                                                                 :text-align    "right"})} (nth product 7)]])
                                         data)]]]])
   :events            {:item-select-change (fn [view-state {item  :item
                                                            event :event}]
                                             (if (aget event "target" "checked")
                                               (update view-state :selected-items remove #{item})
                                               (update view-state :selected-items conj item)))}})