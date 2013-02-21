(ns ticlj.player.medium
  (:use [ticlj.player.unbeatable])
  (:require [ticlj.model.rules :as rules]
            [ticlj.model.board :as board]
            [ticlj.player :as player])
  (:import [ticlj.player Player]))

(defrecord MediumAI [mark]
  Player
  (move [this board]
    (alpha-beta (:mark this) board 4)))
