(ns patient-app.database.database-config)

(def db-password
  (System/getenv "PGPASSWORD")
  )

(def db-user
  (System/getenv "POSTGRES_USER")
  )

(def postgres {
               :subprotocol "postgresql"
               :subname "//127.0.0.1:5432/samurai_test"
               :user db-user
               :password db-password
               :stringtype "unspecified"
               })

