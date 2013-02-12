(ns ticlj.model.player-spec
  (:use [speclj.core] [ticlj.spec-helper] [ticlj.model.player])
  (:import [ticlj.model.player Human UnbeatableAI])
  (:require [ticlj.model.board :as board]))

(describe "ticlj.model.player"
  (context "AI"
    (it "returns a score of 1 for winning"
      (should= 1
               (calculate-score board/x-mark x-winning-game-board)))

    (it "returns a score of 0 for tied game"
      (should= 0
               (calculate-score board/x-mark tied-game-board)))

    (it "returns a score of -1 for losing game"
      (should= -1
               (calculate-score board/x-mark o-winning-game-board)))

    (it "returns max move attributes for a tie game"
      (let [board [board/x-mark board/x-mark board/o-mark
                   board/o-mark board/o-mark board/x-mark
                   board/x-mark board/nomark board/o-mark]]
        (should= 7
                 (:position (max-move board/x-mark board)))))

    (it "returns max move x can make for the win"
      (let [board [board/x-mark board/nomark board/x-mark
                   board/nomark board/nomark board/o-mark
                   board/nomark board/o-mark board/nomark]]
        (should= 1
                 (:position (max-move board/x-mark board)))))

    (it "returns max move o can make"
      (let [board [board/x-mark board/o-mark board/nomark
                   board/nomark board/o-mark board/nomark
                   board/o-mark board/x-mark board/x-mark]]
        (should= 2
                 (:position (max-move board/x-mark board)))))

    (it "returns an index for a move"
      (let [board [board/x-mark board/x-mark board/o-mark
                   board/o-mark board/o-mark board/x-mark
                   board/x-mark board/nomark board/o-mark]]
        (should= 7
                 (move (UnbeatableAI. board/x-mark) board)))))

    ;(it "have an opening move"
      ;(should= 7
               ;(move (UnbeatableAI. board/x-mark) board/empty-board))))

  (context "Human"
    (it "returns an index as the move"
      (with-out-str
        (should= 0
                 (with-in-str "0"
                     (move (Human. board/x-mark) board/empty-board)))))

    (it "requests a move until the move is valid"
      (should (.contains (with-out-str (with-in-str (make-input '("x" 0))
                           (move (Human. board/x-mark) board/empty-board)))
                         "Invalid input, please try again.")))))
