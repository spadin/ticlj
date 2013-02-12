(ns ticlj.model.player.human
  (:require [ticlj.io.cli :as printer]
            [ticlj.model.rules :as rules]
            [ticlj.model.board :as board]
            [ticlj.model.player :as player])
  (:import [ticlj.model.player Player]))

(defrecord Human [mark]
  Player
  (move [this board]
    (try (board/validate-mark-at-index (:mark this) (printer/prompt-player (:mark this)) board)
      (catch Exception e
        (println (str "Invalid move, please try again." e))
        (player/move this board)))))
