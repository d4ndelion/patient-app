(ns patient-app.core
  (:require [reagent.dom :as rdom]
            [patient-app.ui.main-screen :as main-screen]))

(enable-console-print!)

(def dom-root (js/document.getElementById "app"))

(rdom/render [main-screen/main-screen] dom-root)

