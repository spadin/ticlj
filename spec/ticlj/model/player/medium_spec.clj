(ns ticlj.model.player.medium-spec
  (:use [speclj.core]
        [ticlj.spec-helper]
        [ticlj.model.player.medium])
  (:require [ticlj.model.player :as player]
            [ticlj.model.rules :as rules]
            [ticlj.model.board :as board])
  (:import [ticlj.model.player.medium MediumAI]))

(describe "ticlj.game.model.player.medium"
  (it "returns a move to win game"
    (let [board [board/x-mark board/o-mark board/x-mark
                 board/nomark board/o-mark board/nomark
                 board/o-mark board/nomark board/x-mark]
          actual (player/move (MediumAI. board/x-mark) board)]
      (should= 5
               actual))))
