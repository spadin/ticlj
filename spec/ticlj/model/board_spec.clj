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
    (should-throw Exception (set-mark-at-index x-mark 9)))

  (it "throws an error if you try to pick a spot that's taken"
    (set-mark-at-index x-mark 0)
    (should-throw Exception (set-mark-at-index o-mark 0)))

  (it "returns the indices of x-mark's moves"
    (set-mark-at-index x-mark 0)
    (set-mark-at-index x-mark 1)
    (set-mark-at-index x-mark 2)
    (should= #{0 1 2}
             (get-moves x-mark)))

  (it "returns the indices of x-mark's moves"
    (set-mark-at-index o-mark 4)
    (set-mark-at-index o-mark 5)
    (set-mark-at-index o-mark 6)
    (should= #{4 5 6}
             (get-moves o-mark)))

  (it "knows when the board is not full"
    (should= false (full-board?)))

  (it "knows when the board is full"
    (set-mark-at-index x-mark 0)
    (set-mark-at-index o-mark 3)
    (set-mark-at-index x-mark 1)
    (set-mark-at-index o-mark 4)
    (set-mark-at-index x-mark 2)
    (set-mark-at-index o-mark 5)
    (set-mark-at-index x-mark 7)
    (set-mark-at-index o-mark 8)
    (set-mark-at-index x-mark 6)
    (should= true (full-board?))))

