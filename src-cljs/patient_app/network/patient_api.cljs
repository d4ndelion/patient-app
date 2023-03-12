(ns patient-app.network.patient-api
  (:require [patient-app.network.network-configuration :as config]
            [cljs-http.client :as http]
            [clojure.core.async :refer [go <!]]))

(defn get-all-patients
  []
  (go (let [response (<! (http/get (str config/base-url "/patients")))]
    (js/console.log "sended request")
    (prn (:body response)))))

