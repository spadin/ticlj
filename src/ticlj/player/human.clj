(ns ticlj.player.human
  (:require [ticlj.printer.basic :as printer]
            [ticlj.board.basic :as board]
            [ticlj.player.aplayer :as player])
  (:import [ticlj.player.aplayer APlayer]))

(defrecord Human []
  APlayer
  (move [_ board]
    (try (board/validate-mark-at-index (board/current-mark board) (printer/prompt-player (board/current-mark board)) board)
      (catch Exception e
        (println (str "Invalid move, please try again." e))
        (player/move _ board)))))
