(ns ticlj.game.four-by-four.board-spec
  (:use [speclj.core]
        [ticlj.game.four-by-four.board]
        [ticlj.game.base.board :only [size]])
  (:import [ticlj.game.four_by_four.board FourByFourBoard]))

(describe "ticlj.game.four-by-four.board"
  (context "#get-mark-at-index"
    (it "identifies the size of the board to be 16"
      (should= 16
               (size four-by-four-board)))))
