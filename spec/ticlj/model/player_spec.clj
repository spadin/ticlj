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

    (it "choose index 0 as the first move"
      (should= 0
               (move (UnbeatableAI. board/x-mark) board/empty-board))))
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
