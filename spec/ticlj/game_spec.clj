(ns ticlj.game-spec
  (:use [speclj.core] [ticlj.game])
  (:require [ticlj.model.board :as board]))

(defn play-tied-game []
  (board/set-mark-at-index board/x-mark 0)
  (board/set-mark-at-index board/o-mark 1)
  (board/set-mark-at-index board/x-mark 2)
  (board/set-mark-at-index board/o-mark 5)
  (board/set-mark-at-index board/x-mark 3)
  (board/set-mark-at-index board/o-mark 6)
  (board/set-mark-at-index board/x-mark 4)
  (board/set-mark-at-index board/o-mark 8)
  (board/set-mark-at-index board/x-mark 7))

(defn play-x-winning-game []
  (board/set-mark-at-index board/x-mark 0)
  (board/set-mark-at-index board/o-mark 3)
  (board/set-mark-at-index board/x-mark 1)
  (board/set-mark-at-index board/o-mark 4)
  (board/set-mark-at-index board/x-mark 2))

(defn play-o-winning-game []
  (board/set-mark-at-index board/x-mark 0)
  (board/set-mark-at-index board/o-mark 3)
  (board/set-mark-at-index board/x-mark 1)
  (board/set-mark-at-index board/o-mark 4)
  (board/set-mark-at-index board/x-mark 7)
  (board/set-mark-at-index board/o-mark 5))

(describe "ticlj.game"
  (before (board/reset-board))

  (it "determines the next player"
    (should= board/o-mark
             (next-player board/x-mark))
    (should= board/x-mark
             (next-player board/o-mark)))

  (it "determines that x is the winner"
    (play-x-winning-game)
    (should= board/x-mark
             (winner)))

  (it "determines that o is the winner"
    (play-o-winning-game)
    (should= board/o-mark
             (winner)))

  (it "determines that there is a winner"
    (play-x-winning-game)
    (should (winner?)))

  (it "determines that there is no winner"
    (should= false
             (winner?)))

  (it "should know that the game has not ended"
    (should= false
             (gameover?)))

  (it "should know the game is over when there is a winner"
    (play-x-winning-game)
    (should= true
             (gameover?)))

  (it "should know the game is over when the board is full"
    (play-tied-game)
    (should= true
             (gameover?))))
