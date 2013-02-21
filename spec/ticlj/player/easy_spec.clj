(ns ticlj.player.easy-spec
  (:use [speclj.core]
        [ticlj.spec-helper]
        [ticlj.player.easy])
  (:require [ticlj.player :as player]
            [ticlj.model.rules :as rules]
            [ticlj.board.basic :as board])
  (:import [ticlj.player.easy EasyAI]))

(describe "ticlj.player.easy"
  (it "makes the first available move in an empty board"
    (should= 0
             (make-move board/x-mark board/empty-board)))

  (it "make the first available move in a non-empty board"
    (let [board [board/x-mark board/nomark board/nomark
                 board/nomark board/nomark board/nomark
                 board/nomark board/nomark board/nomark]
          actual (make-move board/o-mark board)]
      (should= 1
               actual)))

  (it "makes the first available move via the player/move function"
    (let [board [board/x-mark board/nomark board/nomark
                 board/nomark board/nomark board/nomark
                 board/nomark board/nomark board/nomark]
          actual (player/move (EasyAI. board/o-mark) board)]
    (should= 1
             actual))))
