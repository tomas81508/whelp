(ns whelp.color)

;; Colors
;; https://material.io/guidelines/style/color.html

(def grey-50 "#FAFAFA")
(def grey-100 "#F5F5F5")
(def grey-200 "#EEEEEE")
(def grey-300 "#E0E0E0")

(def white "#ffffff")

(def black-42% "rgba(0,0,0,0.42)")
(def black-54% "rgba(0,0,0,0.54)")
(def black-87% "rgba(0,0,0,0.87)")

(defn get-color [state level number]
  (let [color (condp = level
                :primary :orange
                :secendary :indigo
                level)]
    (condp = color
      :blue (condp = number
              "50" "#E3F2FD"
              "100" "#BBDEFB"
              "200" "#90CAF9"
              "300" "#64B5F6"
              "400" "#42A5F5"
              "500" "#2196F3"
              "600" "#1E88E5"
              "700" "#1976D2"
              "800" "#1565C0"
              "900" "#0D47A1"
              "A100" "#82B1FF"
              "A200" "#448AFF"
              "A400" "#2979FF"
              "A700" "#2962FF")
      :deep-purple (condp = number
                     "50" "#EDE7F6"
                     "100" "#D1C4E9"
                     "200" "#B39DDB"
                     "300" "#9575CD"
                     "400" "#7E57C2"
                     "500" "#673AB7"
                     "600" "#5E35B1"
                     "700" "#512DA8"
                     "800" "#4527A0"
                     "900" "#311B92"
                     "A100" "#B388FF"
                     "A200" "#7C4DFF"
                     "A400" "#651FFF"
                     "A700" "#6200EA")
      :indigo (condp = number
                "50" "#E8EAF6"
                "100" "#C5CAE9"
                "200" "#9FA8DA"
                "300" "#7986CB"
                "400" "#5C6BC0"
                "500" "#3F51B5"
                "600" "#3949AB"
                "700" "#303F9F"
                "800" "#283593"
                "900" "#1A237E"
                "A100" "#8C9EFF"
                "A200" "#536DFE"
                "A400" "#3D5AFE"
                "A700" "#304FFE")
      :orange (condp = number
                "50" "#FFF3E0"
                "100" "#FFE0B2"
                "200" "#FFCC80"
                "300" "#FFB74D"
                "400" "#FFA726"
                "500" "#FF9800"
                "600" "#FB8C00"
                "700" "#F57C00"
                "800" "#EF6C00"
                "900" "#E65100"
                "A100" "#FFD180"
                "A200" "#FFAB40"
                "A400" "#FF9100"
                "A700" "#FF6D00")
      :pink (condp = number
              "50" "#FCE4EC"
              "100" "#F8BBD0"
              "200" "#F48FB1"
              "300" "#F06292"
              "400" "#EC407A"
              "500" "#E91E63"
              "600" "#D81B60"
              "700" "#C2185B"
              "800" "#AD1457"
              "900" "#880E4F"
              "A100" "#FF80AB"
              "A200" "#FF4081"
              "A400" "#F50057"
              "A700" "#C51162")
      :purple (condp = number
                "50" "#F3E5F5"
                "100" "#E1BEE7"
                "200" "#CE93D8"
                "300" "#BA68C8"
                "400" "#AB47BC"
                "500" "#9C27B0"
                "600" "#8E24AA"
                "700" "#7B1FA2"
                "800" "#6A1B9A"
                "900" "#4A148C"
                "A100" "#EA80FC"
                "A200" "#E040FB"
                "A400" "#D500F9"
                "A700" "#AA00FF")
      :red (condp = number
             "50" "#FFEBEE"
             "100" "#FFCDD2"
             "200" "#EF9A9A"
             "300" "#E57373"
             "400" "#EF5350"
             "500" "#F44336"
             "600" "#E53935"
             "700" "#D32F2F"
             "800" "#C62828"
             "900" "#B71C1C"
             "A100" "#FF8A80"
             "A200" "#FF5252"
             "A400" "#FF1744"
             "A700" "#D50000"))))
