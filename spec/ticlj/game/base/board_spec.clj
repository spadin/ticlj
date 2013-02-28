(ns ticlj.game.base.board-spec
  (:use [speclj.core]
        [ticlj.game.base.board]))
        ;[ticlj.rules.game-type :only (*game-type*)])
  ;(:require [ticlj.rules.game-type :as game-type]))

(describe "ticlj.game.base.board"
  (with sample-board-state [:# :X :#
                            :# :# :O
                            :# :# :#])

  (context "#get-mark-at-index"
    (it "returns blank mark when board index is not occupied"
      (should= :#
               (get-mark-at-index base-board (empty-board base-board) 0)))

    (it "returns the X mark when board index is occupied by X"
      (should= :X
               (get-mark-at-index base-board @sample-board-state 1)))

    (it "returns the O mark when board index is occupied by O"
      (should= :O
               (get-mark-at-index base-board @sample-board-state 5))))

  (context "#valid-index?"
    (it "returns true when spot is not taken"
      (should= true
               (valid-index? base-board @sample-board-state 6)))

    (it "returns false when spot is taken"
      (should= false
               (valid-index? base-board @sample-board-state 5)))

    (it "returns false when spot is out of range"
      (should= false
               (valid-index? base-board @sample-board-state 9))))

  (context "#get-move-indices"
    (it "returns a set with the indices that X has taken"
      (should= #{1}
               (get-move-indices base-board @sample-board-state :X)))

    (it "returns a set with the indices that O has taken"
      (should= #{5}
               (get-move-indices base-board @sample-board-state :O))))

  (context "#next-possible-mark"
    (it "determines X is the next possible mark"
      (should= :X
               (next-possible-mark base-board @sample-board-state)))

    (it "determines O is the next possible mark"
      (let [board-state [:# :X :#
                         :# :# :O
                         :# :X :#]]
        (should= :O
                 (next-possible-mark base-board board-state)))))

  (context "#set-mark-at-index"
    (it "returns a new board with the mark in it"
      (let [expected-board-state [:X :X :#
                                  :# :# :O
                                  :# :# :#]]
        (should= expected-board-state
                 (set-mark-at-index base-board @sample-board-state 0))))

    (it "returns another new board with the mark in it"
      (let [initial-board-state  [:X :X :#
                                  :# :# :O
                                  :# :# :#]
            expected-board-state [:X :X :#
                                  :O :# :O
                                  :# :# :#]]
        (should= expected-board-state
                 (set-mark-at-index base-board initial-board-state 3)))))

  (context "#get-empty-indices"
    (it "returns a collection of the empty indices"
      (should= #{0 2 3 4 6 7 8}
               (get-empty-indices base-board @sample-board-state))))

  (context "#full-board?"
    (it "knows when the board is not full"
      (should= false (full-board? base-board (empty-board base-board))))

    (it "knows when the board is not full"
      (let [board-state [:X :O :X
                         :X :O :O
                         :O :X :X]]
      (should= true (full-board? base-board board-state))))))
