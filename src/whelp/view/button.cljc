(ns whelp.view.button
  (:require [whelp.color :as color]
            [whelp.style :as style]
            [whelp.view.paper :refer [paper]]
            [whelp.view.typography :as typography]
            [whelp.view.doc :as doc]))

(def button
  {:render (fn [{dense :dense children :children label :label disabled :disabled}]
             (let [dense (or dense false)
                   disabled (or disabled false)]
               [paper {:depth 0
                       :width "inherit"}
                [:div {:style {:height         (if dense "32px" "36px")
                               :padding-left   "16px"
                               :padding-right  "16px"
                               :vertical-align "middle"
                               :text-transform "uppercase"
                               :font-weight    "500"
                               :min-width "88px"
                               :text-align "center"
                               :margin-left "8px"
                               :margin-right "8px"
                               :color (if disabled color/black-26% color/black-87%)
                               :font-size      (if dense "13px" "14px")
                               :line-height    (if dense "32px" "36px")}}
                 label
                 children]]))})

; <button tabindex="0" type="button" style="border: 10px; box-sizing: border-box; display: inline-block; font-family: Roboto, sans-serif; -webkit-tap-highlight-color: rgba(0, 0, 0, 0); cursor: pointer; text-decoration: none; margin: 0px; padding: 0px; outline: none; font-size: inherit; font-weight: inherit; position: relative; height: 36px; line-height: 36px; min-width: 88px; color: rgb(0, 188, 212); transition: all 450ms cubic-bezier(0.23, 1, 0.32, 1) 0ms; border-radius: 2px; user-select: none; overflow: hidden; background-color: rgba(0, 0, 0, 0); text-align: center;">
; <div>
; <span style="position: relative; padding-left: 16px; padding-right: 16px; vertical-align: middle; letter-spacing: 0px; text-transform: uppercase; font-weight: 500; font-size: 14px;">Primary</span>
; </div>
; </button>

(def button-demo
  {:render (fn [{state-atom :state-atom}]
             [:div
              [doc/showcase-2
               [:div {:style {:padding "5px"}}
                [button {:label "Button"}]]
               [:div {:style {:padding "5px"}}
                [button {:label "Disabled Button"
                         :disabled true}]]
               [:div {:style {:padding "5px"}}
                [button {:label "Dense button"
                         :dense true }]]]])})

