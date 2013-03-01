(ns ticlj.rules.basic
  (:use [ticlj.rules.game-type :only (*game-type*)])
  (:require [ticlj.board.basic :as board]
            [ticlj.rules.game-type :as game-type]))

(defn horizontal-wins [modulus total]
  "Returns a list of horizontal wins"
  (loop [wins []
         from 0
         to (+ from modulus)]
        (if (<= to total)
            (recur (merge wins (set (range from to))) to (+ to modulus))
            wins)))

(defn vertical-wins [modulus total]
  "Returns a list of vertical-wins works with 3x3x3 game type"
  (let [upper-bound (+ (* modulus modulus) (- 0 modulus) 1)
        board-size (* modulus modulus)]
    (loop [wins []
           hop 0
           board 0]
          (let [from (+ hop (* board board-size))]
               (if (<= (+ upper-bound from) total)
                   (recur (merge wins (set (range from (+ upper-bound from) modulus)))
                          (rem (inc hop) modulus)
                          (if (= (inc hop) modulus) (inc board) board))
                  wins)))))

(defn through-nd-wins [modulus total]
  "Returns a list of wins for an n-dimensional board.
   A through wins means the same position in each dimension;
   for example, indices 0,9,18 on a 3x3x3 game."
  (let [board-size (* modulus modulus)]
    (loop [wins []
           from 0]
          (if (< from board-size)
              (recur (merge wins (set (range from total board-size))) (inc from))
              wins))))

(defmulti multi-winning-combinations :game-type)

(defmethod multi-winning-combinations game-type/basic [_]
  (vec (flatten (merge (horizontal-wins 3 9)
                       (vertical-wins 3 9)
                        #{0 4 8} #{2 4 6}))))

(defmethod multi-winning-combinations game-type/four-by-four [_]
  (vec (flatten (merge (horizontal-wins 4 16)
                       (vertical-wins 4 16)
                        #{0 5 10 15} #{12 9 6 3}))))

(defmethod multi-winning-combinations game-type/three-cubed [_]
  (vec (flatten (merge (horizontal-wins 3 27)
                       (vertical-wins 3 27)
                       (through-nd-wins 3 27)
                        #{0 13 26} #{2 13 24} #{8 13 18} #{6 13 20}))))

(defn winning-combinations []
  (multi-winning-combinations {:game-type *game-type*}))

(def is-winner?
  (memoize (fn [mark board]
    (some #(= (set (filter % (board/get-moves mark board))) %) (winning-combinations)))))

(def winner
  (memoize (fn [board]
    (if (is-winner? board/x-mark board)
      board/x-mark
      (if (is-winner? board/o-mark board)
        board/o-mark
        nil)))))

(def winner?
  (memoize (fn [board]
    (not (nil? (winner board))))))

(def gameover?
  (memoize (fn [board]
    (if (winner? board)
      true
      (if (board/full-board? board)
        true
        false)))))
