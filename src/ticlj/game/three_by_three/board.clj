(ns ticlj.game.three-by-three.board
  (:use [ticlj.game.protocol.board])
  (:require [ticlj.game.shared.board :as shared-board]))

(def board-size 9)
(def num-boards 1)

(defrecord ThreeByThreeBoard []
  Board
  (empty-board-state [this] (shared-board/empty-board-state board-size num-boards))
  (valid-index? [this board-state mark] (shared-board/valid-index? board-state mark))
  (get-move-indices [this board-state mark] (shared-board/get-move-indices board-state mark))
  (get-empty-indices [this board-state] (shared-board/get-empty-indices board-state))
  (full-board? [this board-state] (shared-board/full-board? board-state))
  (next-possible-mark [this board-state] (shared-board/next-possible-mark board-state))
  (set-mark-at-index [this board-state index] (shared-board/set-mark-at-index board-state index))
  (get-mark-at-index [this board-state index] (shared-board/get-mark-at-index board-state index)))

(def three-by-three-board (ThreeByThreeBoard.))
