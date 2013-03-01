(ns ticlj.game.three-by-three.logic-spec
  (:use [speclj.core]
        [ticlj.game.three-by-three.logic]
        [ticlj.game.base.logic :only [winning-combinations]])
  (:import [ticlj.game.three_by_three.logic ThreeByThreeLogic]))

(describe "ticlj.game.three-by-three.logic"
  (it "should have 8 winning combinations"
    (should= 8
             (count (winning-combinations three-by-three-logic)))))
