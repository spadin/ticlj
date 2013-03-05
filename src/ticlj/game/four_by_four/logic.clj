(ns ticlj.game.four-by-four.logic
  (:use [ticlj.game.protocol.logic]
        [ticlj.game.shared.winning-combination-maker]
        [ticlj.game.four-by-four.board :only [four-by-four-board]])
  (:require [ticlj.game.shared.logic :as shared-logic]))

(def winning-combinations
  (vec (flatten (merge (horizontal-wins 4 2)
                       (vertical-wins 4 2)
                       #{0 5 10 15} #{12 9 6 3}))))

(defrecord FourByFourLogic [board]
  Logic
  (winner? [this board-state] (shared-logic/winner? board-state winning-combinations))
  (winner [this board-state] (shared-logic/winner board-state winning-combinations))
  (gameover? [this board-state] (shared-logic/gameover? board-state winning-combinations)))

(def four-by-four-logic (FourByFourLogic. four-by-four-board))
