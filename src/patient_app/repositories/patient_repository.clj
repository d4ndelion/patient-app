(ns patient-app.repositories.patient-repository
  (:require [patient-app.database.database-config :refer [postgres]]
            [clojure.java.jdbc :as java-db])
  (:import [org.postgresql.util PSQLException]
           [java.sql BatchUpdateException]))

(defn get-all-patients
  ([]
   (java-db/query postgres "SELECT * FROM samurai_test_patients.patient"))
  ([filter-columns]
   (let [filters {:gender (get filter-columns :gender)
                  :address (get filter-columns :address)
                  :patient_name (get filter-columns :patient_name)
                  :birth_date (if
                                  (= nil (filter-columns :birth_date))
                                nil
                                (java.time.LocalDate/parse (filter-columns :birth_date)))
                  }
         mapped-filters (into {} (filter (comp some? val) filters))]
     (java-db/find-by-keys postgres :samurai_test_patients.patient mapped-filters))))

(defn get-patient-by-id
  [patient-id]
  (java-db/get-by-id postgres :samurai_test_patients.patient patient-id))

(defn create-patient
  [patient]
  (try
    (java-db/insert! postgres :samurai_test_patients.patient patient)
    (catch PSQLException e nil)))

(defn delete-patient
  [patient-id]
  (java-db/delete! postgres :samurai_test_patients.patient ["id = ?" patient-id]))

(defn update-patient
  [patient]
  (try
    (java-db/update! postgres :samurai_test_patients.patient patient ["id = ?" (patient :id)])
    (catch BatchUpdateException e nil)))

