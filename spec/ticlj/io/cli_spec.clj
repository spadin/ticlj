(ns ticlj.io.cli-spec
  (:use [speclj.core] [ticlj.spec-helper] [ticlj.io.cli])
  (:require [ticlj.model.board :as board]))

(describe "ticlj.io.cli"
  (it "prompts player x for a move"
    (should= "Player x what is your move?\n"
             (with-out-str (with-in-str "0" (prompt-player board/x-mark)))))

  (it "prompts player o for a move"
    (should= "Player o what is your move?\n"
             (with-out-str (with-in-str "0" (prompt-player board/o-mark)))))

  (it "rejects invalid input until a valid input is entered"
    (with-out-str (with-in-str (make-input '("bad input" "0"))
      (should= 0 (prompt-integer "enter an integer")))))

  (it "gets user move as an Integer"
    (with-out-str (with-in-str "1"
      (should= 1 (prompt-integer "enter an interger")))))

  (it "return an empty board line string"
    (let [board board/empty-board]
      (should= "   |   |   "
               (line-string 0 board))))

  (it "returns a board line with one mark"
    (let [board [board/x-mark board/nomark board/nomark
                 board/nomark board/nomark board/nomark
                 board/nomark board/nomark board/nomark]]
      (should= " x |   |   "
               (line-string 0 board))))

  (it "prints an empty board"
    (let [board board/empty-board]
      (should= "   |   |   \n---|---|---\n   |   |   \n---|---|---\n   |   |   \n"
               (with-out-str (print-board board)))))

  (it "prints a board with some moves"
    (let [board [board/x-mark board/nomark board/nomark
                 board/nomark board/o-mark board/nomark
                 board/nomark board/x-mark board/nomark]]
      (should= " x |   |   \n---|---|---\n   | o |   \n---|---|---\n   | x |   \n"
               (with-out-str (print-board board)))))

  (it "prompts the user to select a game type"
    (should= "Please select a game.\n1. HvH\n2. HvAI\n3. AIvH\n"
             (with-out-str (with-in-str "1" (prompt-game-type))))))
