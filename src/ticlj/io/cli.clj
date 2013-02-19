(ns ticlj.io.cli
  (:require [ticlj.model.board :as board]))

(def separator "---|---|---")

(defn index-string [board index]
  (let [mark (board/mark-at-index index board)]
    (if (= mark " ") index mark)))

(defn line-string [line board]
  (str " "
       (-> board (index-string (* line 3)))
       " | "
       (-> board (index-string (+ (* line 3) 1)))
       " | "
       (-> board (index-string (+ (* line 3) 2)))
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
  (prompt-integer (str "Player " mark " choose the index of an empty spot for your next move")))

(defn prompt-player-type [player-number]
  (prompt-integer (str "What type of player is player " player-number "?\n1. Human\n2. Unbeatable AI\n3. Easy AI\n4. Medium AI")))
