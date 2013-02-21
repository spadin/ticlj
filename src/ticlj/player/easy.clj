(ns ticlj.player.easy
  (:require [ticlj.model.rules :as rules]
            [ticlj.model.board :as board]
            [ticlj.player :as player])
  (:import [ticlj.player Player]))

(declare make-move)
(defrecord EasyAI [mark]
  Player
  (move [this board]
    (make-move (:mark this) board)))

(defn make-move [mark board]
  (first (board/get-empty-indices board)))
