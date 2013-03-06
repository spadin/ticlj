(ns ticlj.game.protocol.ui)

(defprotocol Ui
  (display-gameover [this winner])
  (print-board [this board-state]))
