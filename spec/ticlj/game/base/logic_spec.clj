(ns ticlj.game.base.logic-spec
  (:use [speclj.core]
        [ticlj.game.base.logic]
        [ticlj.game.base.board :only [base-board]]))

(describe "ticlj.game.base.logic"
  (context "#winning-combinations"
    (it "has 8 winning combinations by default"
      (should= 8
               (count (winning-combinations (base-logic base-board))))))

  (context "ticlj.game.base.logic/winning-combination-met?"
    (it "determines a winning combination is not in the board"
      (let [board-state [:X :# :#
                         :# :# :#
                         :# :# :#]
            winning-combination #{0 1 2}]
        (should= false
                 (winning-combination-met? board-state winning-combination))))

    (it "determines :X has met a winning combination"
      (let [board-state [:X :X :X
                         :# :# :#
                         :# :# :#]
            winning-combination #{0 1 2}]
        (should= :X
                 (winning-combination-met? board-state winning-combination)))))

  (context "#winner?"
    (it "determines there is no winner when the board is not full"
      (let [board-state [:X :# :#
                         :# :# :#
                         :# :# :#]]
        (should= false
                 (winner? (base-logic base-board) board-state))))

    (it "determines there is a winner"
      (let [board-state [:# :# :#
                         :X :X :X
                         :# :# :#]]
        (should= true
                 (winner? (base-logic base-board) board-state)))))

  (context "#winner"
    (it "determines there is no winner when the board is not full"
      (let [board-state [:X :# :#
                         :# :# :#
                         :# :# :#]]
        (should= nil
                 (winner (base-logic base-board) board-state))))

    (it "determines :X is the winner"
      (let [board-state [:# :# :#
                         :X :X :X
                         :# :# :#]]
        (should= :X
                 (winner (base-logic base-board) board-state)))))

  (context "#gameover"
    (it "determines the game is not over"
      (let [board-state [:# :O :#
                         :X :X :#
                         :# :# :#]]
        (should= false
                 (gameover? (base-logic base-board) board-state))))

    (it "determines the game is over when there is a winner"
      (let [board-state [:O :# :X
                         :O :X :#
                         :X :# :#]]
        (should= true
                 (gameover? (base-logic base-board) board-state))))

    (it "determines the game is over when the board is full"
      (let [board-state [:X :O :X
                         :X :O :O
                         :O :X :X]]
        (should= true
                 (gameover? (base-logic base-board) board-state))))))
