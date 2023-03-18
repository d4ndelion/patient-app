(ns patient-app.network.patient-api
  (:require 
    [patient-app.network.network-configuration :as config]
    [cljs-http.client :as http]
    [clojure.core.async :refer [go <!]]))

(defn get-all-patients
  []
  (go (let [response (<! (http/get (str config/base-url "/patients")))]
    (prn (:body response)))))

(defn get-patient 
  [id]
  (go (let [response (<! (http/get (str config/base-url "/patient") 
                                   {:query-params {"id" id} }))] 
    (prn (:body response)))))

(defn create-patient
  [new-patient]
  (go (let [response (<! (http/post (str config/base-url "/patient") 
                                    {
                                     :content-type "application-json"
                                     :accept "application/json"
                                     :body (.stringify js/JSON (clj->js new-patient))
                                    }))]
    (prn (:body response)))))

(defn delete-patient
  [id]
  (go (let [response (<! (http/delete (str config/base-url "/patient")
                                      {
                                       :query-params {"id" id}
                                      }))]
    (prn (:body response)))))

(defn update-patient
  [updated-patient]
  (go (let [response (<! (http/patch (str config/base-url "/patient")
                                     {
                                      :body (.stringify js/JSON (clj->js updated-patient))
                                      }))]
    (prn (:body response)))))

