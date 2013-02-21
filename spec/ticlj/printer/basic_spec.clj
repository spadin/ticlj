(ns ticlj.printer.basic-spec
  (:use [speclj.core]
        [ticlj.spec-helper]
        [ticlj.printer.basic]
        [ticlj.rules.game-type :only (*game-type*)])
  (:require [ticlj.board.basic :as board]
            [ticlj.rules.game-type :as game-type]))

(describe "ticlj.printer.basic"
  (it "prompts player x for a move"
    (should= "Player x choose the index of an empty spot for your next move\n"
             (with-out-str (with-in-str "0" (prompt-player board/x-mark)))))

  (it "prompts player o for a move"
    (should= "Player o choose the index of an empty spot for your next move\n"
             (with-out-str (with-in-str "0" (prompt-player board/o-mark)))))

  (it "rejects invalid input until a valid input is entered"
    (with-out-str (with-in-str (make-input '("bad input" "0"))
      (should= 0 (prompt-integer "enter an integer")))))

  (it "gets user move as an Integer"
    (with-out-str (with-in-str "1"
      (should= 1 (prompt-integer "enter an interger")))))

  (it "prompts player 1 to choose type of player."
    (should= "What type of player is player 1?\n1. Human\n2. Unbeatable AI\n3. Easy AI\n4. Medium AI\n"
             (with-out-str (with-in-str "1" (prompt-player-type 1)))))

  (it "prints a gameover message when that x wins"
    (should= "Game over, x has won\n"
             (with-out-str (print-gameover board/x-mark))))

  (it "prints a tied game message when nobody wins"
    (should= "Game over, tied game\n"
             (with-out-str (print-gameover nil))))

  (it "prompts user to choose a game type"
    (should= "Please choose a game:\n1. 3x3 Tic Tac Toe\n2. 4x4 Tic Tac Toe\n"
             (with-out-str (with-in-str "1" (prompt-game-type)))))

  (context "3x3 game type"
    (it "prints an empty board"
      (let [board (board/empty-board)]
        (should= " 0 | 1 | 2 \n---|---|---\n 3 | 4 | 5 \n---|---|---\n 6 | 7 | 8 \n"
                 (with-out-str (print-board board)))))

    (it "prints a board with some moves"
      (let [board [board/x-mark board/nomark board/nomark
                   board/nomark board/o-mark board/nomark
                   board/nomark board/x-mark board/nomark]]
        (should= " x | 1 | 2 \n---|---|---\n 3 | o | 5 \n---|---|---\n 6 | x | 8 \n"
                 (with-out-str (print-board board))))))

  (context "4x4 game type"
    (it "prints an empty board"
      (binding [*game-type* game-type/four-by-four]
        (let [board (board/empty-board)]
          (should= " 0  | 1  | 2  | 3  \n----|----|----|----\n 4  | 5  | 6  | 7  \n----|----|----|----\n 8  | 9  | 10 | 11 \n----|----|----|----\n 12 | 13 | 14 | 15 \n"
                   (with-out-str (print-board board)))))))
)
