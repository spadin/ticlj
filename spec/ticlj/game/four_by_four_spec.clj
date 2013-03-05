(ns ticlj.game.four-by-four-spec
  (:use [speclj.core]
        [ticlj.game.protocol]
        [ticlj.game.four-by-four :only [four-by-four-game]])
  (:require [ticlj.game.protocol.ui :as ui-protocol]
            [ticlj.game.protocol.board :as board-protocol]
            [ticlj.game.protocol.logic :as logic-protocol]))

(describe "ticlj.game.four-by-four"
  (with sample-board-state [:# :# :# :#
                            :# :# :# :#
                            :# :# :# :#
                            :# :# :# :# ])

  (context "Ui"
    (it "calls ticlj.game.four-by-four.ui/print-board"
      (with-redefs [ticlj.game.four-by-four.ui/four-by-four-ui
                    (reify ui-protocol/Ui (print-board [this board-state] :stubbed))]
        (should= :stubbed
                 (print-board four-by-four-game @sample-board-state))))

    (it "calls ticlj.game.four-by-four.ui/display-gameover"
      (with-redefs [ticlj.game.four-by-four.ui/four-by-four-ui
                    (reify ui-protocol/Ui (display-gameover [this winner] :stubbed))]
        (should= :stubbed
                 (display-gameover four-by-four-game :X)))))

  (context "Logic"
    (it "calls ticlj.game.four-by-four.logic/gameover?"
      (with-redefs [ticlj.game.four-by-four.logic/four-by-four-logic
                    (reify logic-protocol/Logic (gameover? [this board-state] :stubbed))]
        (should= :stubbed
                 (gameover? four-by-four-game @sample-board-state))))

    (it "calls ticlj.game.four-by-four.logic/winner"
      (with-redefs [ticlj.game.four-by-four.logic/four-by-four-logic
                    (reify logic-protocol/Logic (winner [this board-state] :stubbed))]
        (should= :stubbed
                 (winner four-by-four-game @sample-board-state)))))

  (context "Board"
    (it "calls ticlj.game.four-by-four.board/empty-board-state"
      (with-redefs [ticlj.game.four-by-four.board/four-by-four-board
                    (reify board-protocol/Board (empty-board-state [this] :stubbed))]
        (should= :stubbed
                 (empty-board-state four-by-four-game))))

    (it "calls ticlj.game.four-by-four.board/set-mark-at-index"
      (with-redefs [ticlj.game.four-by-four.board/four-by-four-board
                    (reify board-protocol/Board (set-mark-at-index [this board-state index] :stubbed))]
        (should= :stubbed
                 (set-mark-at-index four-by-four-game @sample-board-state 0))))

    (it "calls ticlj.game.four-by-four.board/next-possible-mark"
      (with-redefs [ticlj.game.four-by-four.board/four-by-four-board
                    (reify board-protocol/Board (next-possible-mark [this board-state] :stubbed))]
        (should= :stubbed
                 (next-possible-mark four-by-four-game @sample-board-state))))))

