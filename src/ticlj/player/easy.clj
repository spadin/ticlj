(ns ticlj.player.easy
  (:require [ticlj.board.basic :as board]
            [ticlj.player.aplayer :as player])
  (:import [ticlj.player.aplayer APlayer]))

(declare make-move)
(defrecord EasyAI []
  APlayer
  (move [this board]
    (make-move (board/current-mark board) board)))

(defn make-move [mark board]
  (first (board/get-empty-indices board)))
