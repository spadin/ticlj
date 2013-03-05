(ns ticlj.game.four-by-four.logic-spec
  (:use [speclj.core]
        [ticlj.game.protocol.logic]
        [ticlj.game.four-by-four.logic]))

(describe "ticlj.game.four-by-four.logic"
  (it "should have 10 winning combinations"
    (should= 10
             (count winning-combinations)))

  (context "/winner?"
    (it "determines there is no winner when the board is not full"
      (let [board-state [:X :# :# :#
                         :# :# :# :#
                         :# :# :# :#
                         :# :# :# :#]]
        (should= false
                 (winner? four-by-four-logic board-state)))))

    (it "determines there is a winner"
      (let [board-state [:# :# :# :#
                         :X :X :X :X
                         :O :O :# :#
                         :# :# :O :#]]
        (should= true
                 (winner? four-by-four-logic board-state))))

  (context "/winner"
    (it "determines there is no winner when the board is not full"
      (let [board-state [:X :# :# :#
                         :# :# :# :#
                         :# :# :# :#
                         :# :# :# :#]]
        (should= nil
                 (winner four-by-four-logic board-state))))

    (it "determines :X is the winner"
      (let [board-state [:# :# :# :#
                         :X :X :X :X
                         :O :O :# :#
                         :# :# :O :#]]
        (should= :X
                 (winner four-by-four-logic board-state)))))

  (context "/gameover"
    (it "determines the game is not over"
      (let [board-state [:X :# :# :#
                         :# :# :# :#
                         :# :# :# :#
                         :# :# :# :#]]
        (should= false
                 (gameover? four-by-four-logic board-state))))

    (it "determines the game is over when there is a winner"
      (let [board-state [:# :# :# :#
                         :X :X :X :X
                         :O :O :# :#
                         :# :# :O :#]]
        (should= true
                 (gameover? four-by-four-logic board-state))))

    (it "determines the game is over when the board is full"
      (let [board-state [:X :O :X :O
                         :O :X :O :X
                         :X :O :X :O
                         :X :O :X :O]]
        (should= true
                 (gameover? four-by-four-logic board-state))))))
