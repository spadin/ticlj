(ns ticlj.model.player-spec
  (:use [speclj.core] [ticlj.spec-helper] [ticlj.model.player])
  (:import [ticlj.model.player Human])
  (:require [ticlj.model.board :as board]))

(describe "ticlj.model.player"
  (it "knows its mark"
    (should= board/x-mark
             (mark (Human. board/x-mark))))

  (it "requests a move until the move is valid"
    (should (.contains (with-out-str (with-in-str (make-input '("x" 0))
                         (move (Human. board/x-mark) board/empty-board)))
                       "Invalid input, please try again."))))
