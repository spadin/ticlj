(ns ticlj.io.cli
  (:require [ticlj.model.board :as board]))

(def separator "---|---|---")

(defn line-string [line board]
  (str " "
       (board/mark-at-index (* line 3) board)
       " | "
       (board/mark-at-index (+ (* line 3) 1) board)
       " | "
       (board/mark-at-index (+ (* line 3) 2) board)
       " "))

(defn print-board [board]
  (println (line-string 0 board))
  (println separator)
  (println (line-string 1 board))
  (println separator)
  (println (line-string 2 board)))

(defn prompt-integer [message]
  (println message)
  (try (Integer/parseInt (read-line))
    (catch Exception e
      (println "Invalid input, please try again.")
      (prompt-integer message))))

(defn prompt-player [mark]
  (prompt-integer (str "Player " mark " what is your move?")))

(defn prompt-player-type [player-number]
  (prompt-integer (str "Player " player-number " what type of player are you?\n1. Human\n2. AI")))

(defn prompt-game-type []
  (println "Please select a game.\n1. HvH\n2. HvAI\n3. AIvH"))
