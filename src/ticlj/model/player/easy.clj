(ns ticlj.model.player.easy
  (:require [ticlj.model.rules :as rules]
            [ticlj.model.board :as board]
            [ticlj.model.player :as player])
  (:import [ticlj.model.player Player]))

(defrecord EasyAI [mark]
  Player
  (move [this board]
    0))

(defn make-move [mark board]
  (first (board/get-empty-indices board)))
