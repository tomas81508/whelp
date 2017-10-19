(ns whelp.style)

;; Colors
;; https://material.io/guidelines/style/color.html

(def grey-100 "#F5F5F5")
(def grey-200 "#EEEEEE")
(def grey-300 "#E0E0E0")

(def white "#ffffff")

(def z-elevation-2dp
  {:box-shadow "0 2px 2px 0 rgba(0,0,0,0.14), 0 3px 1px -2px rgba(0,0,0,0.12), 0 1px 5px 0 rgba(0,0,0,0.2)"})

(def black-42% "rgba(0,0,0,0.42)")
(def black-54% "rgba(0,0,0,0.54)")
(def black-87% "rgba(0,0,0,0.87)")

(defn get-color [state level number]
  (let [color (condp = level
                :primary :indigo
                :secendary :green
                level)]
    (condp = color
      :indigo (condp = number
                "500" "#3F51B5"
                "A700" "#304FFE")
      :red (condp = number
             "A400" "#EF5350"))))
