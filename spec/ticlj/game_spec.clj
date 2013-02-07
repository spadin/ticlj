(ns ticlj.game-spec
  (:use [speclj.core] [ticlj.spec-helper] [ticlj.game])
  (:require [ticlj.model.board :as board]))

(describe "ticlj.game"
  (before (board/reset-board))

  (it "requests a move until the move is valid"
    (should (.contains (with-out-str (with-in-str (make-input '("x" 0))
                         (request-move-recur board/x-mark)))
                       "Invalid input, please try again.")))

  (it "prints that the game is a tie"
    (should (.contains (with-out-str (with-in-str (make-input '(0 1 2 5 3 6 4 8 7))
                         (play-recur)))
                       "tied game")))

  (it "prints that x has won the game"
    (should (.contains (with-out-str (with-in-str (make-input '(0 3 1 4 2))
                         (play-recur)))
                       "x has won")))

  (it "prints that o has won the game"
    (should (.contains (with-out-str (with-in-str (make-input '(0 3 1 4 7 5))
                         (play-recur)))
                       "o has won")))

  (it "calls play-recur when game is started"
    (with-redefs [play-recur (fn [] "play-recur-called")]
                 (should= "play-recur-called" (start-game)))))
