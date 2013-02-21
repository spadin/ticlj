(ns ticlj.player.medium-spec
  (:use [speclj.core]
        [ticlj.spec-helper]
        [ticlj.player.medium])
  (:require [ticlj.player :as player]
            [ticlj.model.rules :as rules]
            [ticlj.board.basic :as board])
  (:import [ticlj.player.medium MediumAI]))

(describe "ticlj.player.medium"
  (it "returns a move to win game"
    (let [board [board/x-mark board/o-mark board/x-mark
                 board/nomark board/o-mark board/nomark
                 board/o-mark board/nomark board/x-mark]
          actual (player/move (MediumAI. board/x-mark) board)]
      (should= 5
               actual))))
