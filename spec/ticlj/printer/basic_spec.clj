(ns ticlj.printer.basic-spec
  (:use [speclj.core] [ticlj.spec-helper] [ticlj.printer.basic])
  (:require [ticlj.model.board :as board]))

(describe "ticlj.io.cli"
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

  (it "return an empty board line string"
    (let [board board/empty-board]
      (should= " 0 | 1 | 2 "
               (line-string 0 board))))

  (it "returns a board line with one mark"
    (let [board [board/x-mark board/nomark board/nomark
                 board/nomark board/nomark board/nomark
                 board/nomark board/nomark board/nomark]]
      (should= " x | 1 | 2 "
               (line-string 0 board))))

  (it "prints an empty board"
    (let [board board/empty-board]
      (should= " 0 | 1 | 2 \n---|---|---\n 3 | 4 | 5 \n---|---|---\n 6 | 7 | 8 \n"
               (with-out-str (print-board board)))))

  (it "prints a board with some moves"
    (let [board [board/x-mark board/nomark board/nomark
                 board/nomark board/o-mark board/nomark
                 board/nomark board/x-mark board/nomark]]
      (should= " x | 1 | 2 \n---|---|---\n 3 | o | 5 \n---|---|---\n 6 | x | 8 \n"
               (with-out-str (print-board board)))))

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

)
