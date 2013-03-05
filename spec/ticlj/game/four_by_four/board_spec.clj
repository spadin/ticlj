(ns ticlj.game.four-by-four.board-spec
  (:use [speclj.core]
        [ticlj.game.protocol.board]
        [ticlj.game.four-by-four.board]))

(describe "ticlj.game.four-by-four.board"
  (with sample-empty-board-state (empty-board-state four-by-four-board))
  (with sample-board-state [:# :X :# :#
                            :# :# :O :#
                            :# :# :# :#
                            :# :# :# :#])

  (context "/empty-board-state"
    (it "creates an empty-board-state with 16 elements"
      (should= 16
               (count (empty-board-state four-by-four-board)))))

  (context "/get-mark-at-index"
    (it "returns blank mark when board index is not occupied"
      (should= :#
               (get-mark-at-index four-by-four-board @sample-empty-board-state 0)))

    (it "returns the X mark when board index is occupied by X"
      (should= :X
               (get-mark-at-index four-by-four-board @sample-board-state 1)))

    (it "returns the O mark when board index is occupied by O"
      (should= :O
               (get-mark-at-index four-by-four-board @sample-board-state 6))))

  (context "/valid-index?"
    (it "returns true when spot is not taken"
      (should= true
               (valid-index? four-by-four-board @sample-board-state 7)))

    (it "returns false when spot is taken"
      (should= false
               (valid-index? four-by-four-board @sample-board-state 6)))

    (it "returns false when spot is out of range"
      (should= false
               (valid-index? four-by-four-board @sample-board-state 16))))

  (context "/get-move-indices"
    (it "returns a set with the indices that X has taken"
      (should= #{1}
               (get-move-indices four-by-four-board @sample-board-state :X))))

    (it "returns a set with the indices that O has taken"
      (should= #{6}
               (get-move-indices four-by-four-board @sample-board-state :O)))

  (context "/next-possible-mark"
    (it "determines X is the next possible mark"
      (should= :X
               (next-possible-mark four-by-four-board @sample-board-state)))

    (it "determines O is the next possible mark"
      (let [board-state [:# :X :# :#
                         :# :# :O :#
                         :# :X :# :#
                         :# :# :# :#]]
        (should= :O
                 (next-possible-mark four-by-four-board board-state)))))

  (context "/set-mark-at-index"
    (it "returns a new board with the mark in it"
      (let [expected-board-state [:X :X :# :#
                                  :# :# :O :#
                                  :# :# :# :#
                                  :# :# :# :#]]
        (should= expected-board-state
                 (set-mark-at-index four-by-four-board @sample-board-state 0))))

    (it "returns another new board with the mark in it"
      (let [initial-board-state  [:X :X :# :#
                                  :# :# :O :#
                                  :# :# :# :#
                                  :# :# :# :#]
            expected-board-state [:X :X :# :O
                                  :# :# :O :#
                                  :# :# :# :#
                                  :# :# :# :#]]
        (should= expected-board-state
                 (set-mark-at-index four-by-four-board initial-board-state 3)))))

  (context "/get-empty-indices"
    (it "returns a collection of the empty indices"
      (should= #{0 2 3 4 5 7 8 9 10 11 12 13 14 15}
               (get-empty-indices four-by-four-board @sample-board-state))))

  (context "/full-board?"
    (it "knows when the board is not full"
      (should= false (full-board? four-by-four-board @sample-empty-board-state)))

    (it "knows when the board is full"
      (let [board-state [:X :O :X :O
                         :X :O :O :X
                         :O :X :X :O
                         :X :O :X :O]]
      (should= true (full-board? four-by-four-board board-state))))))
