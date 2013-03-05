(ns ticlj.game.protocol)

(defprotocol Game
  (print-board [this board-state])
  (display-gameover [this winner])
  (gameover? [this board-state])
  (winner [this board-state])
  (empty-board-state [this])
  (set-mark-at-index [this board-state index])
  (next-possible-mark [this board-state]))
