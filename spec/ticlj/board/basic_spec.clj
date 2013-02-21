(ns ticlj.board.basic-spec
  (:use [speclj.core] [ticlj.board.basic]))

(describe "ticlj.basic.board"
  (it "returns blank space when board index is not occupied"
    (should= nomark
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
    (should-throw Exception (validate-mark-at-index "y" 0 empty-board)))

  (it "throws an error when index is not in proper range"
    (should-throw Exception (validate-mark-at-index x-mark 9 empty-board)))

  (it "throws an error if you try to pick a spot that's taken"
    (let [board [x-mark nomark nomark
                 nomark nomark nomark
                 nomark nomark nomark]]
      (should-throw Exception (validate-mark-at-index o-mark 0 board))))

  (it "returns the index of the move"
    (let [board [nomark nomark nomark
                 nomark nomark nomark
                 nomark nomark nomark]]
      (should= 0 (validate-mark-at-index x-mark 0 board))))

  (it "returns a board with the new mark in it"
    (let [board [nomark nomark nomark
                 nomark nomark nomark
                 nomark nomark nomark]
          new-board [x-mark nomark nomark
                     nomark nomark nomark
                     nomark nomark nomark]]
      (should= new-board (set-mark-at-index board x-mark 0))))

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

  (it "returns a collection of the empty indices"
    (let [board [x-mark nomark nomark
                 nomark nomark nomark
                 nomark nomark nomark]]
      (should= [1 2 3 4 5 6 7 8]
               (get-empty-indices board))))

  (it "knows when the board is not full"
    (should= false (full-board? empty-board)))

  (it "knows when the board is full"
    (let [board [x-mark x-mark x-mark
                 o-mark o-mark o-mark
                 x-mark x-mark o-mark]]
      (should= true (full-board? board)))))

