(ns ticlj.model.player.medium
  (:use [ticlj.model.player.unbeatable])
  (:require [ticlj.model.rules :as rules]
            [ticlj.model.board :as board]
            [ticlj.model.player :as player])
  (:import [ticlj.model.player Player]))

(defrecord MediumAI [mark]
  Player
  (move [this board]
    (alpha-beta (:mark this) board 4)))
