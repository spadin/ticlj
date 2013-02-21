(ns ticlj.player.unbeatable-spec
  (:use [speclj.core]
        [ticlj.spec-helper]
        [ticlj.player.unbeatable])
  (:require [ticlj.player :as player]
            [ticlj.rules.basic :as rules]
            [ticlj.board.basic :as board])
  (:import [ticlj.player.unbeatable UnbeatableAI]))

(describe "ticlj.player.unbeatable"
  (it "never returns true when max-depth is nil"
    (should= false
             (max-depth-reached? 1 nil)))

  (it "can identify max-depth has been reached"
    (should= true
             (max-depth-reached? 2 1)))

  (it "can identify max-depth hasn't been reached"
    (should= false
             (max-depth-reached? 1 2)))

  (it "returns a score of 1000 for winning at depth 0"
    (should= 1000
             (calculate-score board/x-mark x-winning-game-board 0 0)))

  (it "returns a score of 0 for tied game"
    (should= 0
             (calculate-score board/x-mark tied-game-board 0 0)))

  (it "returns a score of -1000 for losing game at 0 depth"
    (should= -1000
             (calculate-score board/x-mark o-winning-game-board 0 0)))

  (it "returns the ab-value when game is not over"
    (should= -9999
             (calculate-score board/x-mark board/empty-board 0 -9999)))

  (it "returns the move the fills the board"
    (let [board [board/x-mark board/x-mark board/o-mark
                 board/o-mark board/o-mark board/x-mark
                 board/x-mark board/nomark board/o-mark]
          actual (alpha-beta board/x-mark board nil)]
      (should= 7
              actual)))

  (it "returns a move to win game"
    (let [board [board/x-mark board/o-mark board/x-mark
                 board/nomark board/o-mark board/nomark
                 board/o-mark board/nomark board/x-mark]
          actual (alpha-beta board/x-mark board nil)]
      (should= 5
               actual)))

  (context "slow running tests" (tags :slow-tests)
    (it "returns the best first move"
      (should= 0
              (alpha-beta board/x-mark board/empty-board nil)))

    (it "returns the best second move"
      (let [board [board/x-mark board/nomark board/nomark
                   board/nomark board/nomark board/nomark
                   board/nomark board/nomark board/nomark]
            actual (alpha-beta board/o-mark board nil)]
        (should= 4
                 actual)))))
