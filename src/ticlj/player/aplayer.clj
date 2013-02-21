(ns ticlj.player.aplayer)

(defprotocol APlayer
  (move [this board]))
