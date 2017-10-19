(ns whelp.main
  (:require
    [cljs.core]

    ;; Possible DOM engines:
    ;; - onyxia.engine.react
    ;; - onyxia.engine.inferno
    [onyxia.engine.inferno :as render-engine]
    ;[onyxia.engine.react :as render-engine]
    [onyxia.input.parent-size]
    [onyxia.input.mouse-position]
    [onyxia.input.element-hovered]
    [onyxia.output.at-body-root-view]
    [whelp.view.presentation :as presentation]
    ))

(enable-console-print!)

(defonce state-atom (atom {:components {:text-field {:label "Label"}}}))

(defn paused?
  []
  (:paused (deref state-atom)))

(defn not-paused?
  []
  (not (paused?)))

(defn render! []
  (render-engine/render!
    {:view               [presentation/demo {:state-atom state-atom}]
     :target-element     (js/document.getElementById "app")
     :input-definitions  {"parent-size"     [onyxia.input.parent-size/definition {:should-update? not-paused?}]
                          "mouse-position"  [onyxia.input.mouse-position/definition {:should-update? not-paused?}]
                          "element-hovered" [onyxia.input.element-hovered/definition {:mouse-position-input-definition onyxia.input.mouse-position/definition
                                                                                      :should-update?                  not-paused?}]}
     :output-definitions {"at-body-root-view" onyxia.output.at-body-root-view/definition}}))

(render!)

(add-watch state-atom
           :doc-engine
           (fn [_ _ _ _]
             (render!)))