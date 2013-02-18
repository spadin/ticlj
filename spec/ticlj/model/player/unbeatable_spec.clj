(ns ticlj.model.player.unbeatable-spec
  (:use [speclj.core]
        [ticlj.spec-helper]
        [ticlj.model.player.unbeatable])
  (:require [ticlj.model.player :as player]
            [ticlj.model.rules :as rules]
            [ticlj.model.board :as board])
  (:import [ticlj.model.player.unbeatable UnbeatableAI]))

(describe "ticlj.game.model.player.unbeatable"
  (it "returns a score of 1000 for winning at depth 0"
    (should= 1000
             (calculate-score board/x-mark x-winning-game-board 0)))

  (it "returns a score of 0 for tied game"
    (should= 0
             (calculate-score board/x-mark tied-game-board 0)))

  (it "returns a score of -1000 for losing game at 0 depth"
    (should= -1000
             (calculate-score board/x-mark o-winning-game-board 0)))

  (it "returns the move the fills the board"
    (let [board [board/x-mark board/x-mark board/o-mark
                 board/o-mark board/o-mark board/x-mark
                 board/x-mark board/nomark board/o-mark]
          actual (alpha-beta board/x-mark board)]
      (should= 7
              actual)))

  (it "returns a move to win game"
    (let [board [board/x-mark board/o-mark board/x-mark
                 board/nomark board/o-mark board/nomark
                 board/o-mark board/nomark board/x-mark]
          actual (alpha-beta board/x-mark board)]
      (should= 5
               actual)))

  (context "slow running tests" (tags :slow-tests)
    (it "returns the best first move"
      (should= 0
              (alpha-beta board/x-mark board/empty-board)))

    (it "returns the best second move"
      (let [board [board/x-mark board/nomark board/nomark
                   board/nomark board/nomark board/nomark
                   board/nomark board/nomark board/nomark]
            actual (alpha-beta board/o-mark board)]
        (should= 4
                 actual)))))
