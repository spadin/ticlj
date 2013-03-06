(ns ticlj.player.medium
  (:use [ticlj.player.protocol]
        [ticlj.player.unbeatable :only [alpha-beta]]))

(defrecord MediumAI []
  Player
  (move [this game board-state]
    (alpha-beta game board-state 4)))

(def medium-ai-player (MediumAI.))
