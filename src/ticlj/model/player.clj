(ns ticlj.model.player
  (:require [ticlj.io.cli :as printer]
            [ticlj.model.rules :as rules]
            [ticlj.model.board :as board]))

(defprotocol Player
  (move [this board]))

(defrecord Human [mark]
  Player
  (move [this board]
    (try (board/validate-mark-at-index (:mark this) (printer/prompt-player (:mark this)) board)
      (catch Exception e
        (println (str "Invalid move, please try again." e))
        (move this board)))))

(defrecord UnbeatableAI [mark]
  Player
  (move [this board]
    0))

(defn calculate-score [mark board]
  (if (= (rules/winner board) mark)
    1
    (if (= (rules/winner board) (rules/next-player mark))
      -1
      0)))
