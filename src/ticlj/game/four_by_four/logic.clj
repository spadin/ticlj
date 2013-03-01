(ns ticlj.game.four-by-four.logic
  (:use [ticlj.game.base.logic :only [logic-defaults]]
        [ticlj.game.base.winning-combination-helper]
        [ticlj.game.four-by-four.board :only [four-by-four-board]]))

(defrecord FourByFourLogic [board])
(def four-by-four-logic (FourByFourLogic. four-by-four-board))

(extend FourByFourLogic
  ticlj.game.base.logic/Logic
  (merge logic-defaults
         {:winning-combinations
          (fn [this]
            (vec (flatten (merge (horizontal-wins 4 2)
                                 (vertical-wins 4 2)
                                 #{0 5 10 15} #{12 9 6 3}))))}))

