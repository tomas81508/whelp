(ns whelp.view.navigation-drawer
  (:require [whelp.color :as color]
            [whelp.style :as style]))

(def navigation-drawer
  {:input  [{:name      "element-hovered"
             :input-key :hovered-row}]
   :render (fn [{hovered-row :hovered-row
                 view-state  :view-state
                 items       :items
                 width       :width
                 height      :height}]
             [:div {:style {:width       width
                            :height      height
                            :font-family "Roboto, sans-serif"
                            :font-size   "14px"
                            :font-weight 700
                            :color       color/black-87%
                            :padding-top "8px"}}
              (map (fn [item]
                     [:div {:style {:height        "48px"
                                    ;; "Use 16dp horizontal margins on mobile and 24dp on tablet"
                                    :padding-left  "24px"
                                    :padding-right "24px"
                                    :display       "flex"
                                    :align-items   "center"
                                    :cursor        "pointer"}}
                      (:title item)])
                   items)])})

(def navigation-drawer-demo
  {:render (fn [{state-atom :state-atom}]
             [:div {:style {:display     "flex"
                            :align-items "top"}}
              [:div {:style {:width        "360px"
                             :margin-right "40px"}}]
              [:div {:style {:width            "720px"
                             :height           "720px"
                             :padding          "64px 64px 64px 64px"
                             :box-sizing       "border-box"
                             :display          "flex"
                             :align-items      "center"
                             :justify-content  "center"
                             :background-color color/grey-200}}
               [:div {:style {:width      "256px"
                              :background color/white}}
                [navigation-drawer {:width  "256px"         ;; 64*4 (standard increment for tablet)
                                    :height "512px"
                                    :items  [{:title "Short title"}
                                             {:title "A little longer title"}
                                             {:title "Another title"}]}]]]])})