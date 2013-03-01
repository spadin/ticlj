(ns ticlj.game.four-by-four.logic-spec
  (:use [speclj.core]
        [ticlj.game.four-by-four.logic]
        [ticlj.game.base.logic :only [winning-combinations]])
  (:import [ticlj.game.four_by_four.logic FourByFourLogic]))

(describe "ticlj.game.four-by-four.logic"
  (it "should have 10 winning combinations"
    (should= 10
             (count (winning-combinations four-by-four-logic)))))


