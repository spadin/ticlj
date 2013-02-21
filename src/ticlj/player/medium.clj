(ns ticlj.player.medium
  (:use [ticlj.player.unbeatable])
  (:require [ticlj.rules.basic :as rules]
            [ticlj.board.basic :as board]
            [ticlj.player.aplayer :as player])
  (:import [ticlj.player.aplayer APlayer]))

(defrecord MediumAI [mark]
  APlayer
  (move [this board]
    (alpha-beta (:mark this) board 4)))
