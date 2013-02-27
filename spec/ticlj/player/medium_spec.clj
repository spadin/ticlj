(ns ticlj.player.medium-spec
  (:use [speclj.core]
        [ticlj.spec-helper]
        [ticlj.player.medium]
        [ticlj.rules.game-type :only (*game-type*)])
  (:require [ticlj.player.aplayer :as player]
            [ticlj.rules.basic :as rules]
            [ticlj.board.basic :as board]
            [ticlj.rules.game-type :as game-type])
  (:import [ticlj.player.medium MediumAI]))

(describe "ticlj.player.medium"
  (it "returns a move to win game"
    (let [board [board/x-mark board/o-mark board/x-mark
                 board/nomark board/o-mark board/nomark
                 board/o-mark board/nomark board/x-mark]
          actual (player/move (MediumAI.) board)]
      (should= 5
               actual)))

  (it "returns a max-depth of 4 for a basic game type"
    (should= 4
             (determine-max-depth)))

  (it "returns a max-depth of 2 for a four-by-four game type"
    (binding [*game-type* game-type/four-by-four]
      (should= 2
               (determine-max-depth))))
)
