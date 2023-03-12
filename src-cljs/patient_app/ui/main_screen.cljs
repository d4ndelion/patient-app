(ns patient-app.ui.main-screen
  (:require [patient-app.network.patient-api :refer [get-all-patients]]))

(defn patient-item
  [patient]
  [:div patient])

(defn main-screen
  []
  (let [patients (get-all-patients)]
    (patient-item patients)))

