(defproject org.clojars.sandropadin/ticlj "0.1.0-SNAPSHOT"
  :description "Tic Tac Toe Game in Clojure"
  :url "https://github.com/spadin/ticlj"
  :dependencies [[org.clojure/clojure "1.5.0"]]
  :plugins [[speclj "2.5.0"]
            [lein-marginalia "0.7.1"]]
  :test-paths ["spec/"]
  :profiles {:dev {:dependencies [[lein-marginalia "0.7.1"]
                                  [speclj "2.5.0"]]}}
  :main ticlj.core)
