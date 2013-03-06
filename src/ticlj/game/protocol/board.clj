(ns ticlj.game.protocol.board)

(defprotocol Board
  (empty-board-state [this])
  (valid-index? [this board-state index])
  (get-move-indices [this board-state mark])
  (get-empty-indices [this board-state])
  (full-board? [this board-state])
  (next-possible-mark [this board-state])
  (set-mark-at-index [this board-state index])
  (get-mark-at-index [this board-state index]))
