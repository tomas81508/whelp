(ns whelp.view.card
  (:require [whelp.color :as color]
            [whelp.style :as style]
            [whelp.view.paper :refer [paper]]
            [whelp.view.typography :as typography]
            [whelp.view.doc :as doc]))

(def card
  {:render (fn [{depth           :depth
                 width           :width
                 height          :height
                 margin          :margin
                 image           :image
                 primary-title   :primary-title
                 supporting-text :supporting-text}]
             [paper {:depth 3
                     :width width}
              ;; An optional header

              ;; Rich media
              (when image
                [:img {:style {:border-top-left-radius  "inherit"
                               :border-top-right-radius "inherit"
                               :width                   "100%"}
                       :src   (:src image)}])

              ;; A primary title
              (when primary-title
                [:div {:style {:margin "24px 16px 16px 16px"}}
                 [typography/headline primary-title]])


              ;; Supporting text
              (when primary-title
                [:div {:style {:margin "16px 16px 24px 16px"}}
                 [typography/body-1 supporting-text]])

              ;; Actions

              ])})

(def card-demo
  {:render (fn [{state-atom :state-atom}]
             (let [size-parameters {:width  "100px"
                                    :height "100px"
                                    :margin "20px"}]
               [:div
                [doc/showcase-2
                 [card {:width           "350px"
                        :image           {:src "asset/image/Onyxia-16-9.jpg"}
                        :primary-title   "Onyxia, dragon mother"
                        :supporting-text "Onyxia long manipulated the Stormwind Court by disguising herself as Lady Katrana Prestor. You would have thought that the giant wings and scales would have been a giveaway."}]]]))})

