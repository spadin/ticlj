(ns ticlj.model.player.easy-spec
  (:use [speclj.core]
        [ticlj.spec-helper]
        [ticlj.model.player.easy])
  (:require [ticlj.model.player :as player]
            [ticlj.model.rules :as rules]
            [ticlj.model.board :as board])
  (:import [ticlj.model.player.easy EasyAI]))

(describe "ticlj.game.model.player.easy"
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
