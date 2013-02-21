(ns ticlj.player)

(defprotocol Player
  (move [this board]))
