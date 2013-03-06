(ns ticlj.player.protocol)

(defprotocol Player
  (move [this game board-state]))
