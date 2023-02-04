(ns patient-app.services.patient-service
  (:require [patient-app.repositories.patient-repository :as repo]
            [clojure.data.json :as json]
            [ring.util.request :refer [body-string]]))

(def ok-request-message {:message "OK"})

(def bad-request-message {:message "BAD_REQUEST"})

(def is-successful [1])

(def is-failed [0])

(defn success-response
  ([]
   {:status 200
    :headers {"Content-Type" "application-json"}
    :body (json/write-str ok-request-message)
    }
   )
  ([body]
  {:status 200
   :headers {"Content-Type" "application-json"}
   :body (json/write-str body)
   }))

(defn fail-response
  ([]
   {:status 400
    :headers {"Content-Type" "application-json"}
    :body (json/write-str bad-request-message)
    })
  ([body]
  {:status 400
   :headers {"Content-Type" "application-json"}
   :body (json/write-str {:message body})
   }))

(defn get-patients
  [request]
  (let [params (get request :params)]
    (if (empty? params)
      (success-response (repo/get-all-patients))
      (success-response (repo/get-all-patients params))
    )))

(defn get-patient
  [request]
  (let [patient-id (get (get request :params) :id)
        patient (repo/get-patient-by-id patient-id)]
    (if (= patient nil)
      (fail-response)
      (success-response patient))))

(defn create-patient
  [request]
  (let [patient-body (json/read-str (body-string request) :key-fn keyword)
        mapped-body (assoc patient-body :birth_date (java.time.LocalDate/parse (get patient-body :birth_date)))
        patient (repo/create-patient mapped-body)]
    (if (= patient nil) (fail-response) (success-response (first patient)))))

(defn delete-patient
  [request]
  (let [patient-id (Long/parseLong (get (get request :params) :id))
        query-result (repo/delete-patient patient-id)]
    (if (= query-result is-successful) (success-response) (fail-response))))

(defn update-patient
  [request]
  (let [patient-body (json/read-str (body-string request) :key-fn keyword)
        birth-date (get patient-body :birth_date)
        mapped-body (if (= birth-date nil)
                      patient-body
                      (assoc patient-body :birth_date (java.time.LocalDate/parse (get patient-body :birth_date))))
        query-result (repo/update-patient mapped-body)]
    (if (= query-result is-successful) (success-response) (fail-response))))

