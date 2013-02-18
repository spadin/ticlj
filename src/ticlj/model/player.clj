(ns ticlj.model.player)

(defprotocol Player
  (move [this board]))
