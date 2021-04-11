(defproject record-sort-gr-client "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[clj-http "3.12.0"]
                 [compojure "1.6.2"]
                 [hiccup "1.0.5"]
                 [org.clojure/clojure "1.10.1"]
                 [ring "1.9.2"]]
  :min-lein-version "2.0.0"
  :uberjar-name "record-sort-gr-client.jar"
  :main record-sort-gr-client.core
  :repl-options {:init-ns record-sort-gr-client.core}
  :profiles {:dev
             {:main record-sort-gr-client.core/-dev-main}})
