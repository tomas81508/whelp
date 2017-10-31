(ns whelp.view.navigation-drawer
  (:require [whelp.color :as color]
            [whelp.style :as style]
            [whelp.view.doc :as doc]))

(def navigation-drawer
  {:input  [{:name      "element-hovered"
             :input-key :hovered-row}
            {:name      "element-active"
             :input-key :element-active-item}]
   :render (fn [{hovered-row         :hovered-row
                 view-state          :view-state
                 items               :items
                 width               :width
                 height              :height
                 active-item         :active-item           ;; The active item as decided by the parent view.
                 element-active-item :element-active-item   ;; The DOM active item (i.e., pressed or in other ways activated).
                 on-item-selected    :on-item-selected}]
             [:div {:style {:width       width
                            :height      height
                            :font-family "Roboto, sans-serif"
                            :font-size   "14px"
                            :font-weight 700
                            :color       color/black-87%
                            :padding-top "8px"}}
              (map (fn [item]
                     [:div {:key                  (hash item)
                            :element-active-value item
                            :style                (merge {:height        "48px"
                                                          ;; "Use 16dp horizontal margins on mobile and 24dp on tablet"
                                                          :padding-left  "24px"
                                                          :padding-right "24px"
                                                          :display       "flex"
                                                          :align-items   "center"
                                                          :cursor        "pointer"}
                                                         (when (or (= item active-item)
                                                                   (= item element-active-item))
                                                           {:background color/grey-200}))
                            :on-click             (fn []
                                                    (when on-item-selected
                                                      (on-item-selected {:item item})))}
                      (:title item)])
                   items)])})

(def navigation-drawer-demo
  {:get-initial-state (fn []
                        {:active-item nil})
   :render            (fn [{state-atom :state-atom}]
                        [:div
                         [doc/module-1]
                         [doc/showcase-2
                          [:div {:style {:width      "256px"
                                         :background color/white}}
                           [navigation-drawer {:width            "256px" ;; 64*4 (standard increment for tablet)
                                               :height           "512px"
                                               :items            [{:title "Short title"}
                                                                  {:title "A little longer title"}
                                                                  {:title "Another title"}]
                                               :active-item      (:active-item (deref state-atom))
                                               :on-item-selected (fn [{item :item}]
                                                                   (swap! state-atom assoc :active-item item))}]]]])})