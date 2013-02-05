(ns ticlj.model.board-spec
  (:use [speclj.core] [ticlj.model.board]))

(describe "ticlj.board-state"
  (before (reset-board))

  (it "returns nil when board index is not occupied"
    (should= " "
             (mark-at-index 0)))

  (it "returns x when player x has selected that index"
    (set-mark-at-index x-mark 0)
    (should= x-mark
             (mark-at-index 0)))

  (it "return o when player o has selected that index"
    (set-mark-at-index o-mark 0)
    (should= o-mark
             (mark-at-index 0)))

  (it "throws an error when mark is not recognized"
    (should-throw Exception (set-mark-at-index "y" 0))))
