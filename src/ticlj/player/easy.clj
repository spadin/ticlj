(ns ticlj.player.easy
  (:use [ticlj.player.protocol]
        [ticlj.game.protocol]
        [ticlj.game.protocol.board :only [get-empty-indices]]))

(declare make-move)
(defrecord EasyAI []
  Player
  (move [this game board-state]
    (make-move (get-board game) board-state)))

(def easy-ai-player (EasyAI.))

(defn make-move [board board-state]
  (first (get-empty-indices board board-state)))
