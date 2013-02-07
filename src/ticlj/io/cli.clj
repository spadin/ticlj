(ns ticlj.io.cli
  (:require [ticlj.model.board :as board]))

(def separator "---|---|---")

(defn line-string [line]
  (str " "
       (board/mark-at-index (* line 3))
       " | "
       (board/mark-at-index (+ (* line 3) 1))
       " | "
       (board/mark-at-index (+ (* line 3) 2))
       " "))

(defn print-board []
  (println (line-string 0))
  (println separator)
  (println (line-string 1))
  (println separator)
  (println (line-string 2)))

(defn prompt-player-recur [mark]
  (println (str "Player " mark " what is your move?"))
  (try (Integer/parseInt (read-line))
    (catch Exception e
      (println "Invalid input, please try again.")
      (prompt-player-recur mark))))

(defn prompt-player [mark]
  (prompt-player-recur mark))
  ;(trampoline prompt-player-recur mark))
