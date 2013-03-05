(ns ticlj.game.three-by-three
  (:use [ticlj.game.protocol]
        [ticlj.game.three-by-three.ui :only [three-by-three-ui]]
        [ticlj.game.three-by-three.logic :only [three-by-three-logic]]
        [ticlj.game.three-by-three.board :only [three-by-three-board]])
  (:require [ticlj.game.protocol.ui :as ui-protocol]
            [ticlj.game.protocol.logic :as logic-protocol]
            [ticlj.game.protocol.board :as board-protocol]))

(defrecord ThreeByThreeGame []
  Game
  (print-board [this board-state]
    (ui-protocol/print-board three-by-three-ui board-state))
  (display-gameover [this winner]
    (ui-protocol/display-gameover three-by-three-ui winner))
  (gameover? [this board-state]
    (logic-protocol/gameover? three-by-three-logic board-state))
  (winner [this board-state]
    (logic-protocol/winner three-by-three-logic board-state))
  (empty-board-state [this]
    (board-protocol/empty-board-state three-by-three-board))
  (set-mark-at-index [this board-state index]
    (board-protocol/set-mark-at-index three-by-three-board board-state index))
  (next-possible-mark [this board-state]
    (board-protocol/next-possible-mark three-by-three-board board-state)))

(def three-by-three-game (ThreeByThreeGame.))
