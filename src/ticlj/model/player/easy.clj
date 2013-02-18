(ns ticlj.model.player.easy
  (:require [ticlj.model.rules :as rules]
            [ticlj.model.board :as board]
            [ticlj.model.player :as player])
  (:import [ticlj.model.player Player]))

(declare make-move)
(defrecord EasyAI [mark]
  Player
  (move [this board]
    (make-move (:mark this) board)))

(defn make-move [mark board]
  (first (board/get-empty-indices board)))