(ns ticlj.game.three-by-three.board-spec
  (:use [speclj.core]
        [ticlj.game.three-by-three.board])
  (:require [ticlj.game.base.board :as board])
  (:import [ticlj.game.three_by_three.board ThreeByThreeBoard]
           [ticlj.game.base.board Board]))

(describe "ticlj.game.three-by-three.board"
  (context "#get-mark-at-index"
    (it "identifies the size of the board to be 9"
      (should= 9
               (board/size three-by-three-board)))))
