(ns ticlj.model.board-spec
  (:use [speclj.core] [ticlj.model.board]))

(describe "ticlj.model.board"
  (it "returns blank space when board index is not occupied"
    (should= " "
             (mark-at-index 0 empty-board)))

  (it "returns x when player x has selected that index"
    (let [board [x-mark nomark nomark
                 nomark nomark nomark
                 nomark nomark nomark]]
      (should= x-mark
               (mark-at-index 0 board))))

  (it "return o when player o has selected that index"
    (let [board [o-mark nomark nomark
                 nomark nomark nomark
                 nomark nomark nomark]]
      (should= o-mark
               (mark-at-index 0 board))))

  (it "throws an error when mark is not recognized"
    (should-throw Exception (set-mark-at-index "y" 0 empty-board)))

  (it "throws an error when index is not in proper range"
    (should-throw Exception (set-mark-at-index x-mark 9 empty-board)))

  (it "throws an error if you try to pick a spot that's taken"
    (let [board [x-mark nomark nomark
                 nomark nomark nomark
                 nomark nomark nomark]]
      (should-throw Exception (set-mark-at-index o-mark 0 board))))

  (it "returns the indices of x-mark's moves"
    (let [board [x-mark x-mark x-mark
                 nomark nomark nomark
                 nomark nomark nomark]]
      (should= #{0 1 2}
               (get-moves x-mark board))))

  (it "returns the indices of o-mark's moves"
    (let [board [nomark nomark nomark
                 nomark o-mark o-mark
                 o-mark nomark nomark]]
      (should= #{4 5 6}
               (get-moves o-mark board))))

  (it "knows when the board is not full"
    (should= false (full-board? empty-board)))

  (it "knows when the board is full"
    (let [board [x-mark x-mark x-mark
                 o-mark o-mark o-mark
                 x-mark x-mark o-mark]]
      (should= true (full-board? board)))))

