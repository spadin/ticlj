(ns ticlj.game.base.winning-combination-helper)

(defn horizontal-wins [modulus dimensions]
  "Returns a list of horizontal wins"
  (let [total (int (Math/pow modulus dimensions))]
    (loop [wins []
           from 0
           to (+ from modulus)]
          (if (<= to total)
              (recur (merge wins (set (range from to))) to (+ to modulus))
              wins))))

(defn vertical-wins [modulus dimensions]
  "Returns a list of vertical-wins works with 3x3x3 game type"
  (let [total (int (Math/pow modulus dimensions))
        upper-bound (+ (* modulus modulus) (- 0 modulus) 1)
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

(defn through-wins [modulus dimensions]
  "Returns a list of wins for an n-dimensional board.
   A through wins means the same position in each dimension;
   for example, indices 0,9,18 on a 3x3x3 game."
  (let [total (int (Math/pow modulus dimensions))
        board-size (* modulus modulus)]
    (loop [wins []
           from 0]
          (if (< from board-size)
              (recur (merge wins (set (range from total board-size))) (inc from))
              wins))))
