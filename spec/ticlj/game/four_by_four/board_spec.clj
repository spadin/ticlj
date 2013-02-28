(ns ticlj.game.four-by-four.board-spec
  (:use [speclj.core]
        [ticlj.game.four-by-four.board])
  (:require [ticlj.game.base.board :as board])
  (:import [ticlj.game.four_by_four.board FourByFourBoard]
           [ticlj.game.base.board Board]))

(describe "ticlj.game.four-by-four.board"
  (context "#get-mark-at-index"
    (it "identifies the size of the board to be 16"
      (should= 16
               (board/size four-by-four-board)))))
