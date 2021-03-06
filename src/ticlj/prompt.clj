(ns ticlj.prompt
  (:require [ticlj.io.printer :as printer]
            [ticlj.io.reader :as reader]))

(defn prompt-for-move [mark]
  (printer/print-line (str "Player " (name mark) " please choose an index for your move."))
  (let [move (reader/read-integer)]
    (printer/print-line "")
    move))

(defn prompt-for-choice [message choices]
  (printer/print-line message)
    (doseq [idx (range (count choices))]
      (printer/print-line (str (inc idx) ". " (:name (nth choices idx)))))
    (let [idx (dec (reader/read-integer-between 1 (count choices)))
          choice (:value (nth choices idx))]
      (printer/print-line "")
      choice))

(defn prompt-for-game-type [game-types]
  (prompt-for-choice "Please choose a game type:" game-types))

(defn prompt-for-player-type [player-types]
     (prompt-for-choice "What type of player is this?" player-types))

