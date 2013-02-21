(ns ticlj.player.human
  (:require [ticlj.printer.basic :as printer]
            [ticlj.model.rules :as rules]
            [ticlj.board.basic :as board]
            [ticlj.player :as player])
  (:import [ticlj.player Player]))

(defrecord Human [mark]
  Player
  (move [this board]
    (try (board/validate-mark-at-index (:mark this) (printer/prompt-player (:mark this)) board)
      (catch Exception e
        (println (str "Invalid move, please try again." e))
        (player/move this board)))))
