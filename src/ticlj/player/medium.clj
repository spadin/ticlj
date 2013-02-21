(ns ticlj.player.medium
  (:use [ticlj.player.unbeatable])
  (:require [ticlj.rules.basic :as rules]
            [ticlj.board.basic :as board]
            [ticlj.player :as player])
  (:import [ticlj.player Player]))

(defrecord MediumAI [mark]
  Player
  (move [this board]
    (alpha-beta (:mark this) board 4)))
