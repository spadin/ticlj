(ns ticlj.game.three-by-three.logic
  (:use [ticlj.game.protocol.logic]
        [ticlj.game.shared.winning-combination-maker]
        [ticlj.game.three-by-three.board :only [three-by-three-board]])
  (:require [ticlj.game.shared.logic :as shared-logic]))

(def winning-combinations
  (vec (flatten (merge (horizontal-wins 3 2)
                       (vertical-wins 3 2)
                       #{0 4 8} #{2 4 6}))))

(defrecord ThreeByThreeLogic [board]
  Logic
  (winner? [this board-state] (shared-logic/winner? board-state winning-combinations))
  (winner [this board-state] (shared-logic/winner board-state winning-combinations))
  (gameover? [this board-state] (shared-logic/gameover? board-state winning-combinations)))

(def three-by-three-logic (ThreeByThreeLogic. three-by-three-board))
