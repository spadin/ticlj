(ns ticlj.player.human-spec
  (:use [speclj.core]
        [ticlj.spec-helper]
        [ticlj.player.human])
  (:require [ticlj.model.board :as board]
            [ticlj.player :as player])
  (:import [ticlj.player.human Human]))


(describe "ticlj.player.human"
  (it "returns an index as the move"
    (with-out-str
      (should= 0
               (with-in-str "0"
                   (player/move (Human. board/x-mark) board/empty-board)))))

  (it "requests a move until the move is valid"
    (should (.contains (with-out-str (with-in-str (make-input '("x" 0))
                         (player/move (Human. board/x-mark) board/empty-board)))
                       "Invalid input, please try again."))))
