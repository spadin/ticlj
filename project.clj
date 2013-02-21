(defproject ticlj "0.1.0-SNAPSHOT"
  :description "Tic Tac Toe Game in Clojure"
  :url "https://github.com/spadin/ticlj"
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [speclj "2.5.0"]]
  :plugins [[speclj "2.5.0"]]
  :test-paths ["spec/"]
  :main ticlj.core)
