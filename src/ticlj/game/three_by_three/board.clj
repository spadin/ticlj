(ns ticlj.game.three-by-three.board
  (:use [ticlj.game.base.board :only [board-defaults]]))

(defrecord ThreeByThreeBoard [])
(def three-by-three-board (ThreeByThreeBoard.))

(extend ThreeByThreeBoard
  ticlj.game.base.board/Board
  board-defaults)
