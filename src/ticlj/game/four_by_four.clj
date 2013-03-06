(ns ticlj.game.four-by-four
  (:use [ticlj.game.protocol]
        [ticlj.game.four-by-four.ui :only [four-by-four-ui]]
        [ticlj.game.four-by-four.logic :only [four-by-four-logic]]
        [ticlj.game.four-by-four.board :only [four-by-four-board]])
  (:require [ticlj.game.protocol.ui :as ui-protocol]
            [ticlj.game.protocol.logic :as logic-protocol]
            [ticlj.game.protocol.board :as board-protocol]))

(defrecord FourByFourGame []
  Game
  (get-logic [this]
    four-by-four-logic)
  (get-ui [this]
    four-by-four-ui)
  (get-board [this]
    four-by-four-board)
  (print-board [this board-state]
    (ui-protocol/print-board four-by-four-ui board-state))
  (display-gameover [this winner]
    (ui-protocol/display-gameover four-by-four-ui winner))
  (gameover? [this board-state]
    (logic-protocol/gameover? four-by-four-logic board-state))
  (winner [this board-state]
    (logic-protocol/winner four-by-four-logic board-state))
  (empty-board-state [this]
    (board-protocol/empty-board-state four-by-four-board))
  (set-mark-at-index [this board-state index]
    (board-protocol/set-mark-at-index four-by-four-board board-state index))
  (next-possible-mark [this board-state]
    (board-protocol/next-possible-mark four-by-four-board board-state)))

(def four-by-four-game (FourByFourGame.))

