(ns ticlj.game.three-by-three.logic-spec
  (:use [speclj.core]
        [ticlj.game.protocol.logic]
        [ticlj.game.three-by-three.logic]))

(describe "ticlj.game.three-by-three.logic"
  (it "should have 8 winning combinations"
    (should= 8
             (count winning-combinations)))

  (context "/winner?"
    (it "determines there is no winner when the board is not full"
      (let [board-state [:X :# :#
                         :# :# :#
                         :# :# :#]]
        (should= false
                 (winner? three-by-three-logic board-state)))))

    (it "determines there is a winner"
      (let [board-state [:# :# :#
                         :X :X :X
                         :# :# :#]]
        (should= true
                 (winner? three-by-three-logic board-state))))

  (context "/winner"
    (it "determines there is no winner when the board is not full"
      (let [board-state [:X :# :#
                         :# :# :#
                         :# :# :#]]
        (should= nil
                 (winner three-by-three-logic board-state))))

    (it "determines :X is the winner"
      (let [board-state [:# :# :#
                         :X :X :X
                         :# :# :#]]
        (should= :X
                 (winner three-by-three-logic board-state)))))

  (context "/gameover"
    (it "determines the game is not over"
      (let [board-state [:# :O :#
                         :X :X :#
                         :# :# :#]]
        (should= false
                 (gameover? three-by-three-logic board-state))))

    (it "determines the game is over when there is a winner"
      (let [board-state [:O :# :X
                         :O :X :#
                         :X :# :#]]
        (should= true
                 (gameover? three-by-three-logic board-state))))

    (it "determines the game is over when the board is full"
      (let [board-state [:X :O :X
                         :X :O :O
                         :O :X :X]]
        (should= true
                 (gameover? three-by-three-logic board-state))))))
