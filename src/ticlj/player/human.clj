(ns ticlj.player.human
  (:require [ticlj.printer.basic :as printer]
            [ticlj.rules.basic :as rules]
            [ticlj.board.basic :as board]
            [ticlj.player.aplayer :as player])
  (:import [ticlj.player.aplayer APlayer]))

(defrecord Human [mark]
  APlayer
  (move [this board]
    (try (board/validate-mark-at-index (:mark this) (printer/prompt-player (:mark this)) board)
      (catch Exception e
        (println (str "Invalid move, please try again." e))
        (player/move this board)))))
