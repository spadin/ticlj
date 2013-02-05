(ns ticlj.io.cli
  (:require [ticlj.model.board :as board]))

(def separator "---|---|---")

(defn line-string [a b c]
  (str " "
       (board/mark-at-index a)
       " | "
       (board/mark-at-index b)
       " | "
       (board/mark-at-index c)
       " "))

(defn print-board []
  (println (line-string 0 1 2))
  (println separator)
  (println (line-string 3 4 5))
  (println separator)
  (println (line-string 6 7 8)))

(defn prompt-player-recur [mark]
  (println (str "Player " mark " what is your move?"))
  (try (Integer/parseInt (read-line))
    (catch Exception e
      (println "Invalid input, please try again.")
      #(prompt-player-recur mark))))

(defn prompt-player [mark]
  (trampoline prompt-player-recur mark))
