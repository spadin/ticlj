(ns ticlj.game.protocol.ui)

(defprotocol Ui
  (prompt-for-move [this mark])
  (prompt-for-choice [this message choices])
  (prompt-for-game-type [this game-types])
  (prompt-for-player-type [this player-types])
  (display-gameover [this winner])
  (print-board [this board-state]))
