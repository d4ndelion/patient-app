(ns patient-app.ui.main-screen
  (:require [reagent.core :as r]
            [patient-app.network.patient-api :refer [get-all-patients get-patient create-patient delete-patient update-patient]]))

(defn patient-item
  [patient]
  [:h1 patient])

(defn main-screen
  []
  (patient-item "Hello World!"))

