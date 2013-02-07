(ns ticlj.io.cli-spec
  (:use [speclj.core] [ticlj.spec-helper] [ticlj.io.cli])
  (:require [ticlj.model.board :as board]))

(describe "ticlj.printer.cli"
  (before (board/reset-board))

  (it "prompts player x for a move"
    (should= "Player x what is your move?\n"
             (with-out-str (with-in-str "0" (prompt-player board/x-mark)))))

  (it "prompts player o for a move"
    (should= "Player o what is your move?\n"
             (with-out-str (with-in-str "0" (prompt-player board/o-mark)))))

  (it "rejects invalid input until a valid input is entered"
    (with-out-str (with-in-str (make-input '("bad input" "0"))
      (should= 0 (prompt-player board/x-mark)))))

  (it "gets user move as an Integer"
    (with-out-str (with-in-str "1"
      (should= 1 (prompt-player board/x-mark)))))

  (it "prints an empty board"
    (should= "   |   |   \n---|---|---\n   |   |   \n---|---|---\n   |   |   \n"
             (with-out-str (print-board))))

  (it "prints a board with some moves"
    (board/set-mark-at-index board/x-mark 0)
    (board/set-mark-at-index board/o-mark 4)
    (board/set-mark-at-index board/x-mark 7)
    (should= " x |   |   \n---|---|---\n   | o |   \n---|---|---\n   | x |   \n"
             (with-out-str (print-board)))))

