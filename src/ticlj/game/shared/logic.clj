(ns ticlj.game.shared.logic
  (:use [ticlj.game.shared.board :only [full-board?]]))

;(defprotocol Logic
  ;(winning-combinations [this])
  ;(winner? [this board-state])
  ;(winner [this board-state])
  ;(gameover? [this board-state]))

;(defrecord BaseLogic [board])
;(defn base-logic [board] (BaseLogic. board))

(defn winning-combination-met?
  "Returns false or the mark that met the winning-combination"
  [board winning-combination]
  (reduce (fn [memo index]
            (if (and (not= memo :#) (= memo (nth board index))) memo false))
          (nth board (first winning-combination))
          winning-combination))

(defn winner [board-state winning-combinations]
 (some (fn [winning-combination]
                     (winning-combination-met? board-state winning-combination))
                 winning-combinations))

(defn winner? [board-state winning-combinations]
     (not (nil? (winner board-state winning-combinations))))

(defn gameover? [board-state winning-combinations]
     (or (winner? board-state winning-combinations) (full-board? board-state)))

;(def logic-defaults
  ;{:winning-combinations
   ;(fn [this]
     ;(vec (flatten (merge (horizontal-wins 3 2)
                          ;(vertical-wins 3 2)
                          ;#{0 4 8} #{2 4 6}))))

   ;:winner
   ;(fn [this board-state]
     ;(some (fn [winning-combination]
                         ;(winning-combination-met? board-state winning-combination))
                     ;(winning-combinations this)))


   ;:gameover?
   ;(fn [this board-state]
     ;(or (winner? this board-state) (full-board? (:board this) board-state)))})

;(extend BaseLogic
  ;Logic
  ;logic-defaults)



