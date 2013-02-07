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

(defn prompt-integer [message]
  (println message)
  (try (Integer/parseInt (read-line))
    (catch Exception e
      (println "Invalid input, please try again.")
      (prompt-integer message))))

(defn prompt-player [mark]
  (prompt-integer (str "Player " mark " what is your move?")))

(defn prompt-game-type []
  (println "Please select a game.\n1. HvH\n2. HvAI\n3. AIvH"))
