(ns ticlj.model.board-spec
  (:use [speclj.core] [ticlj.model.board]))

(describe "ticlj.model.board"
  (before (reset-board))

  (it "returns blank space when board index is not occupied"
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
    (should-throw Exception (set-mark-at-index "y" 0)))

  (it "throws an error when index is not in proper range"
    (should-throw Exception (set-mark-at-index x-mark 20))))
  (it "throws an error if you try to pick a spot that's taken"
    (set-mark-at-index x-mark 0)
    (should-throw Exception (set-mark-at-index o-mark 0)))

