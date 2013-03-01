(ns ticlj.game.three-by-three.logic
  (:use [ticlj.game.base.logic :only [logic-defaults]]
        [ticlj.game.base.winning-combination-helper]
        [ticlj.game.three-by-three.board :only [three-by-three-board]]))

(defrecord ThreeByThreeLogic [board])
(def three-by-three-logic (ThreeByThreeLogic. three-by-three-board))

(extend ThreeByThreeLogic
  ticlj.game.base.logic/Logic
  logic-defaults)

