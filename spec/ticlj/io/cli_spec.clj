(ns ticlj.io.cli-spec
  (:use [speclj.core] [ticlj.io.cli])
  (:require [ticlj.model.board :as board]))

; helps with passing multiple inputs when using with-in-str
; http://mikeebert.tumblr.com/post/32243344470/mocking-input-in-clojure-thanks-colin
(defn make-input [coll]
  (apply str (interleave coll (repeat "\n"))))

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
      (should= 0 (prompt-player "x")))))

  (it "prints an empty board"
    (should= "   |   |   \n---|---|---\n   |   |   \n---|---|---\n   |   |   \n"
             (with-out-str (print-board)))))
