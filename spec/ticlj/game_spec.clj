(ns ticlj.game-spec
  (:use [speclj.core] [ticlj.spec-helper] [ticlj.game])
  (:require [ticlj.model.board :as board]))

(describe "ticlj.game"
  (before (board/reset-board))

  (it "should print that the game is a tie"
    (should (.contains (with-out-str (with-in-str (make-input '(0 1 2 5 3 6 4 8 7))
                         (start-game)))
                        "tied game")))

  (it "should print that x has won the game"
    (should (.contains (with-out-str (with-in-str (make-input '(0 3 1 4 2))
                         (start-game)))
                        "x has won")))

  (it "should print that o has won the game"
    (should (.contains (with-out-str (with-in-str (make-input '(0 3 1 4 7 5))
                         (start-game)))
                        "o has won"))))
