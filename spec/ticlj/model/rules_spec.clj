(ns ticlj.model.rules-spec
  (:use [speclj.core] [ticlj.model.rules])
  (:require [ticlj.model.board :as board]))

(def tied-game-board
  [board/x-mark board/o-mark board/x-mark
   board/x-mark board/x-mark board/o-mark
   board/o-mark board/x-mark board/o-mark])

(def x-winning-game-board
  [board/x-mark board/x-mark board/x-mark
   board/o-mark board/o-mark board/nomark
   board/nomark board/nomark board/nomark])

(def o-winning-game-board
  [board/x-mark board/x-mark board/nomark
   board/o-mark board/o-mark board/o-mark
   board/nomark board/x-mark board/nomark])

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

  (it "should know that the game has not ended"
    (should= false
             (gameover? board/empty-board)))

  (it "should know the game is over when there is a winner"
    (should= true
             (gameover? x-winning-game-board)))

  (it "should know the game is over when the board is full"
    (should= true
             (gameover? tied-game-board))))
