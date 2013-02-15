(ns ticlj.model.player.unbeatable-spec
  (:use [speclj.core]
        [ticlj.spec-helper]
        [ticlj.model.player.unbeatable])
  (:require [ticlj.model.player :as player]
            [ticlj.model.rules :as rules]
            [ticlj.model.board :as board])
  (:import [ticlj.model.player.unbeatable UnbeatableAI]))

(describe "ticlj.game.model.player.unbeatable"
  (it "returns a score of 1 for winning"
    (should= 1
             (calculate-score board/x-mark x-winning-game-board)))

  (it "returns a score of 0 for tied game"
    (should= 0
             (calculate-score board/x-mark tied-game-board)))

  (it "returns a score of -1 for losing game"
    (should= -1
             (calculate-score board/x-mark o-winning-game-board)))

  (context "alpha-beta pruning" (tags :ab)
    (it "returns a score of 0 for a tied game"
      (should= 0
               (alpha-beta-score board/x-mark tied-game-board 0)))

    (it "returns a score of 999 for a tied game"
      (should= 999
               (alpha-beta-score board/x-mark x-winning-game-board 1)))

    (it "returns a move for a tied game"
      (let [board [board/x-mark board/x-mark board/o-mark
                   board/o-mark board/o-mark board/x-mark
                   board/x-mark board/nomark board/o-mark]
            actual (alpha-beta board/x-mark board/x-mark board 0 -9999 9999 nil)]
        (should= 7
                 (:position (reduce (fn [memo val]
                                      (if (> (:score val) (:score memo))
                                        val
                                        memo)) (:scores actual))))))

    (it "returns a move to win game"
      (let [board [board/x-mark board/o-mark board/x-mark
                   board/nomark board/o-mark board/nomark
                   board/o-mark board/nomark board/x-mark]
            actual (alpha-beta board/x-mark board/x-mark board 0 -9999 9999 nil)]
        (should= 5
                 (:position (reduce (fn [memo val]
                                      (if (> (:score val) (:score memo))
                                        val
                                        memo)) (:scores actual)))))))

  (it "returns max move attributes for a tie game"
    (let [board [board/x-mark board/x-mark board/o-mark
                 board/o-mark board/o-mark board/x-mark
                 board/x-mark board/nomark board/o-mark]]
      (should= 7
               (:position (max-move board/x-mark board 0)))))

  (it "returns max move x can make for the win"
    (let [board [board/x-mark board/nomark board/x-mark
                 board/nomark board/nomark board/o-mark
                 board/nomark board/o-mark board/nomark]]
      (should= 1
               (:position (max-move board/x-mark board 0)))))

  (it "returns max move o can make"
    (let [board [board/x-mark board/o-mark board/nomark
                 board/nomark board/o-mark board/nomark
                 board/o-mark board/x-mark board/x-mark]]
      (should= 2
               (:position (max-move board/x-mark board 0)))))

  (it "returns an index for a move"
    (let [board [board/x-mark board/x-mark board/o-mark
                 board/o-mark board/o-mark board/x-mark
                 board/x-mark board/nomark board/o-mark]]
      (should= 7
               (player/move (UnbeatableAI. board/x-mark) board)))))
