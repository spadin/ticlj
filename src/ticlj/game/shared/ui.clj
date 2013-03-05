(ns ticlj.game.shared.ui
  (:require [ticlj.game.shared.printer :as printer]
            [ticlj.game.shared.reader :as reader]))

(defn prompt-for-move [mark]
  (printer/print-line (str "Player " (name mark) " please choose an index for your move."))
  (reader/read-integer))

(defn prompt-for-choice [message choices]
  (printer/print-line message)
    (doseq [idx (range (count choices))]
      (printer/print-line (str (inc idx) ". " (:name (nth choices idx)))))
    (let [idx (dec (reader/read-integer-between 1 (count choices)))]
      (:value (nth choices idx))))

(defn display-gameover [winner]
  (if (nil? winner)
      (printer/print-line "Game over, tied game.")
      (printer/print-line (str "Game over, " (name winner) " won."))))

(defn prompt-for-game-type [game-types]
  (prompt-for-choice "Please choose a game type:" game-types))

(defn prompt-for-player-type [player-types]
     (prompt-for-choice "What type of player is this?" player-types))
