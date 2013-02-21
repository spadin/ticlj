(ns ticlj.model.rules-spec
  (:use [speclj.core] [ticlj.spec-helper] [ticlj.model.rules])
  (:require [ticlj.board.basic :as board]))

(describe "ticlj.model.rules"
  (it "determines the next player"
    (should= board/o-mark
             (next-player board/x-mark))
    (should= board/x-mark
             (next-player board/o-mark)))

  (it "determines that x is the winner"
    (should= board/x-mark
             (winner x-winning-game-board)))

  (it "determines that o is the winner"
    (should= board/o-mark
             (winner o-winning-game-board)))

  (it "determines that there is a winner"
    (should (winner? x-winning-game-board)))

  (it "determines that there is no winner"
    (should= false
             (winner? board/empty-board)))

  (it "knows that the game has not ended"
    (should= false
             (gameover? board/empty-board)))

  (it "knows the game is over when there is a winner"
    (should= true
             (gameover? x-winning-game-board)))

  (it "knows the game is over when the board is full"
    (should= true
             (gameover? tied-game-board))))
