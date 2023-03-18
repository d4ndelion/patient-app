(ns patient-app.core
  (:require [org.httpkit.server :as server]
            [compojure.core :refer [defroutes GET POST DELETE PATCH]]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults]]
            [ring.middleware.cors :refer [wrap-cors]]
            [patient-app.services.patient-service :refer [get-patients create-patient delete-patient update-patient get-patient]])
  (:gen-class))

(defroutes app-routes
  (GET "/" [] "Welcome to Samurai Test App!")
  (GET "/patients" [] get-patients)
  (GET "/patient" [] get-patient)
  (POST "/patient" [] create-patient)
  (DELETE "/patient" [] delete-patient)
  (PATCH "/patient" [] update-patient)
  (route/not-found "Page not found"))

(def app
  (-> app-routes
      (wrap-cors :access-control-allow-credentials "true"
        :acess-control-allow-headers #{"accept" "content-type"}
        :access-control-allow-origin [#".*"]
        :access-control-allow-methods #{:get :post :delete :patch})
      (wrap-defaults api-defaults)))

(defn -main
  [& args]
  (let [port (Integer/parseInt "3000")]
    (server/run-server #'app {:port port})
    (println (str "Server running at http://127.0.0.1:" port "/"))))

