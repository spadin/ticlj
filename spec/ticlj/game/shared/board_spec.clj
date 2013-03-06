(ns ticlj.game.shared.board-spec
  (:use [speclj.core]
        [ticlj.game.shared.board]))

(describe "ticlj.game.shared.board"
  (with sample-board-size 9)
  (with sample-num-boards 1)
  (with sample-empty-board-state (empty-board-state 9 1))
  (with sample-board-state [:# :X :#
                            :# :# :O
                            :# :# :#])

  (context "/empty-board-state"
    (it "returns an empty-board with 9 spaces."
      (should= 9
               (count (empty-board-state 9 1))))

    (it "returns an empty-board with 9 spaces."
      (should= 16
               (count (empty-board-state 16 1))))

    (it "returns an empty-board with 9 spaces."
      (should= 27
               (count (empty-board-state 9 3)))))

  (context "/get-mark-at-index"
    (it "returns blank mark when board index is not occupied"
      (should= :#
               (get-mark-at-index @sample-empty-board-state 0)))

    (it "returns the X mark when board index is occupied by X"
      (should= :X
               (get-mark-at-index @sample-board-state 1)))

    (it "returns the O mark when board index is occupied by O"
      (should= :O
               (get-mark-at-index @sample-board-state 5))))

  (context "/valid-index?"
    (it "returns true when spot is not taken"
      (should= true
               (valid-index? @sample-board-state 6)))

    (it "returns false when spot is taken"
      (should= false
               (valid-index? @sample-board-state 5)))

    (it "returns false when spot is out of range"
      (should= false
               (valid-index? @sample-board-state 9))))

  (context "/get-move-indices"
    (it "returns a set with the indices that X has taken"
      (should= #{1}
               (get-move-indices @sample-board-state :X))))

    (it "returns a set with the indices that O has taken"
      (should= #{5}
               (get-move-indices @sample-board-state :O)))

  (context "/next-possible-mark"
    (it "determines X is the next possible mark"
      (should= :X
               (next-possible-mark @sample-board-state)))

    (it "determines O is the next possible mark"
      (let [board-state [:# :X :#
                         :# :# :O
                         :# :X :#]]
        (should= :O
                 (next-possible-mark board-state)))))

  (context "/set-mark-at-index"
    (it "returns a new board with the mark in it"
      (let [expected-board-state [:X :X :#
                                  :# :# :O
                                  :# :# :#]]
        (should= expected-board-state
                 (set-mark-at-index @sample-board-state 0))))

    (it "returns another new board with the mark in it"
      (let [initial-board-state  [:X :X :#
                                  :# :# :O
                                  :# :# :#]
            expected-board-state [:X :X :#
                                  :O :# :O
                                  :# :# :#]]
        (should= expected-board-state
                 (set-mark-at-index initial-board-state 3)))))

  (context "/get-empty-indices"
    (it "returns a collection of the empty indices"
      (should= #{0 2 3 4 6 7 8}
               (get-empty-indices @sample-board-state))))

  (context "/full-board?"
    (it "knows when the board is not full"
      (should= false (full-board? @sample-empty-board-state)))

    (it "knows when the board is full"
      (let [board-state [:X :O :X
                         :X :O :O
                         :O :X :X]]
      (should= true (full-board? board-state))))))
