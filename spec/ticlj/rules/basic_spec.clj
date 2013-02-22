(ns ticlj.rules.basic-spec
  (:use [speclj.core]
        [ticlj.spec-helper]
        [ticlj.rules.basic]
        [ticlj.rules.game-type :only (*game-type*)])
  (:require [ticlj.board.basic :as board]
            [ticlj.rules.game-type :as game-type]))

(describe "ticlj.rules.basic"
  (it "determines the next player"
    (should= board/o-mark
             (next-player board/x-mark))
    (should= board/x-mark
             (next-player board/o-mark)))

  (context "3x3 game type"
    (it "determines that x is the winner"
      (should= board/x-mark
               (winner x-winning-game-board-basic)))

    (it "determines that o is the winner"
      (should= board/o-mark
               (winner o-winning-game-board-basic)))

    (it "determines that there is a winner"
      (should (winner? x-winning-game-board-basic)))

    (it "determines that there is no winner"
      (should= false
               (winner? (board/empty-board))))

    (it "knows that the game has not ended"
      (should= false
               (gameover? (board/empty-board))))

    (it "knows the game is over when there is a winner"
      (should= true
               (gameover? x-winning-game-board-basic)))

    (it "knows the game is over when the board is full"
      (should= true
               (gameover? tied-game-board-basic))))

  (context "4x4 game type"
    (it "determines that x is the winner"
      (binding [*game-type* game-type/four-by-four]
        (should= board/x-mark
                 (winner x-winning-game-board-four-by-four))))

    (it "determines that o is the winner"
      (binding [*game-type* game-type/four-by-four]
        (should= board/o-mark
                 (winner o-winning-game-board-four-by-four))))

    (it "determines that there is a winner"
      (binding [*game-type* game-type/four-by-four]
        (should (winner? x-winning-game-board-four-by-four))))

    (it "determines that there is no winner"
      (binding [*game-type* game-type/four-by-four]
        (should= false
                 (winner? (board/empty-board)))))

    (it "knows that the game has not ended"
      (binding [*game-type* game-type/four-by-four]
        (should= false
                 (gameover? (board/empty-board)))))

    (it "knows the game is over when there is a winner"
      (binding [*game-type* game-type/four-by-four]
        (should= true
                 (gameover? x-winning-game-board-four-by-four))))

    (it "knows the game is over when the board is full"
      (binding [*game-type* game-type/four-by-four]
        (should= true
                 (gameover? tied-game-board-four-by-four)))))

  (context "3x3x3 game type"
    (it "determines that x is the winner"
      (binding [*game-type* game-type/three-cubed]
        (should= board/x-mark
                 (winner x-winning-game-board-three-cubed))))

    (it "determines that o is the winner"
      (binding [*game-type* game-type/three-cubed]
        (should= board/o-mark
                 (winner o-winning-game-board-three-cubed))))

    (it "determines that there is a winner"
      (binding [*game-type* game-type/three-cubed]
        (should (winner? x-winning-game-board-three-cubed))))

    (it "determines that there is no winner"
      (binding [*game-type* game-type/three-cubed]
        (should= false
                 (winner? (board/empty-board)))))

    (it "knows that the game has not ended"
      (binding [*game-type* game-type/three-cubed]
        (should= false
                 (gameover? (board/empty-board)))))

    (it "knows the game is over when there is a winner"
      (binding [*game-type* game-type/three-cubed]
        (should= true
                 (gameover? x-winning-game-board-three-cubed))))

    (it "knows the game is over when the board is full"
      (binding [*game-type* game-type/three-cubed]
        (should= true
                 (gameover? tied-game-board-three-cubed))))))
