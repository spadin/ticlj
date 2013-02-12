(ns ticlj.model.player.human-spec
  (:use [speclj.core]
        [ticlj.spec-helper]
        [ticlj.model.player.human])
  (:require [ticlj.model.board :as board]
            [ticlj.model.player :as player])
  (:import [ticlj.model.player.human Human]))


(describe "ticlj.model.player.human"
  (it "returns an index as the move"
    (with-out-str
      (should= 0
               (with-in-str "0"
                   (player/move (Human. board/x-mark) board/empty-board)))))

  (it "requests a move until the move is valid"
    (should (.contains (with-out-str (with-in-str (make-input '("x" 0))
                         (player/move (Human. board/x-mark) board/empty-board)))
                       "Invalid input, please try again."))))
