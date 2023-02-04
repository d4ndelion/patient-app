(defproject patient-app "0.1.0-SNAPSHOT"
  :description "FIXME: write this!"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :min-lein-version "2.9.1"

  :dependencies [[org.clojure/clojure "1.10.0"]
                 [org.clojure/clojurescript "1.10.773"]
                 [org.clojure/core.async  "0.4.500"]
                 [compojure "1.7.0"]
                 [http-kit "2.7.0-alpha1"]
                 [ring/ring-defaults "0.3.4"]
                 [org.clojure/data.json "2.4.0"]
                 [org.clojure/java.jdbc "0.7.12"]
                 [org.postgresql/postgresql "42.5.1"]
                 [reagent "1.1.1"]
                 [cljsjs/react "17.0.2-0"]
                 [cljsjs/react-dom "17.0.2-0"]]

  :plugins [[lein-figwheel "0.5.20"]
            [lein-cljsbuild "1.1.7" :exclusions [[org.clojure/clojure]]]]

  :main patient-app.core

  :source-paths ["src" "src-cljs"]

  :cljsbuild {:builds
              [{:id "dev"
                :source-paths ["src-cljs"]

                :figwheel {:open-urls ["http://localhost:3449/index.html"]}

                :compiler {:main patient-app.core
                           :asset-path "js/compiled/out"
                           :output-to "resources/public/js/compiled/patient_app.js"
                           :output-dir "resources/public/js/compiled/out"
                           :source-map-timestamp true
                           :preloads [devtools.preload]}}
               {:id "min"
                :source-paths ["src-cljs"]
                :compiler {:output-to "resources/public/js/compiled/patient_app.js"
                           :main patient-app.core
                           :optimizations :advanced
                           :pretty-print false}}]}

  :figwheel {
             :css-dirs ["resources/public/css"]
             }

  :profiles {:dev {:dependencies [[binaryage/devtools "1.0.0"]
                                  [figwheel-sidecar "0.5.20"]]
                   :source-paths ["src-cljs" "dev" "src"]
                   :clean-targets ^{:protect false} ["resources/public/js/compiled"
                                                     :target-path]}})

