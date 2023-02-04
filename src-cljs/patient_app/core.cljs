(ns patient-app.core
  (:require [reagent.core :as r]
            [reagent.dom :as rdom]))

(enable-console-print!)

(def dom-root (js/document.getElementById "app"))

(defn kek
  []
  [:p "Hello World!"])

(rdom/render [kek] dom-root)

