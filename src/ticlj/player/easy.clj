(ns ticlj.player.easy
  (:require [ticlj.rules.basic :as rules]
            [ticlj.board.basic :as board]
            [ticlj.player.aplayer :as player])
  (:import [ticlj.player.aplayer APlayer]))

(declare make-move)
(defrecord EasyAI [mark]
  APlayer
  (move [this board]
    (make-move (:mark this) board)))

(defn make-move [mark board]
  (first (board/get-empty-indices board)))
