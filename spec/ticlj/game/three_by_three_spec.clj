(ns ticlj.game.three-by-three-spec
  (:use [speclj.core]
        [ticlj.game.protocol]
        [ticlj.game.three-by-three :only [three-by-three-game]])
  (:require [ticlj.game.protocol.ui :as ui-protocol]
            [ticlj.game.protocol.board :as board-protocol]
            [ticlj.game.protocol.logic :as logic-protocol]))

(describe "ticlj.game.three-by-three"
  (with sample-board-state [:# :# :#
                            :# :# :#
                            :# :# :#])

  (context "Ui"
    (it "calls ticlj.game.three-by-three.ui/print-board"
      (with-redefs [ticlj.game.three-by-three.ui/three-by-three-ui
                    (reify ui-protocol/Ui (print-board [this board-state] :stubbed))]
        (should= :stubbed
                 (print-board three-by-three-game @sample-board-state))))

    (it "calls ticlj.game.three-by-three.ui/display-gameover"
      (with-redefs [ticlj.game.three-by-three.ui/three-by-three-ui
                    (reify ui-protocol/Ui (display-gameover [this winner] :stubbed))]
        (should= :stubbed
                 (display-gameover three-by-three-game :X)))))

  (context "Logic"
    (it "calls ticlj.game.three-by-three.logic/gameover?"
      (with-redefs [ticlj.game.three-by-three.logic/three-by-three-logic
                    (reify logic-protocol/Logic (gameover? [this board-state] :stubbed))]
        (should= :stubbed
                 (gameover? three-by-three-game @sample-board-state))))

    (it "calls ticlj.game.three-by-three.logic/winner"
      (with-redefs [ticlj.game.three-by-three.logic/three-by-three-logic
                    (reify logic-protocol/Logic (winner [this board-state] :stubbed))]
        (should= :stubbed
                 (winner three-by-three-game @sample-board-state)))))

  (context "Board"
    (it "calls ticlj.game.three-by-three.board/empty-board-state"
      (with-redefs [ticlj.game.three-by-three.board/three-by-three-board
                    (reify board-protocol/Board (empty-board-state [this] :stubbed))]
        (should= :stubbed
                 (empty-board-state three-by-three-game))))

    (it "calls ticlj.game.three-by-three.board/set-mark-at-index"
      (with-redefs [ticlj.game.three-by-three.board/three-by-three-board
                    (reify board-protocol/Board (set-mark-at-index [this board-state index] :stubbed))]
        (should= :stubbed
                 (set-mark-at-index three-by-three-game @sample-board-state 0))))

    (it "calls ticlj.game.three-by-three.board/next-possible-mark"
      (with-redefs [ticlj.game.three-by-three.board/three-by-three-board
                    (reify board-protocol/Board (next-possible-mark [this board-state] :stubbed))]
        (should= :stubbed
                 (next-possible-mark three-by-three-game @sample-board-state))))))
