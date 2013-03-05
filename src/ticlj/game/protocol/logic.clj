(ns ticlj.game.protocol.logic)

(defprotocol Logic
  (winner? [this board-state])
  (winner [this board-state])
  (gameover? [this board-state]))
