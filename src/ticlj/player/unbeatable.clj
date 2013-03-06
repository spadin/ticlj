(ns ticlj.player.unbeatable
  (:use [ticlj.player.protocol]
        [ticlj.game.protocol]
        [ticlj.game.protocol.board :only [get-empty-indices]]))

(declare alpha-beta)
(declare max-value)
(declare min-value)

(defrecord UnbeatableAI []
  Player
  (move [this game board-state]
    (alpha-beta game board-state nil)))

(def unbeatable-ai-player (UnbeatableAI.))

(def max-mark-winner?
  (memoize (fn [game max-mark board-state]
    (= (winner game board-state) max-mark))))

(def min-mark-winner?
  (memoize (fn [game max-mark board-state]
    (let [winner (winner game board-state)]
      (and
        (not (nil? winner))
        (not= winner max-mark))))))

(def calculate-score
  (memoize (fn [game max-mark board-state depth ab-value]
    (if (gameover? game board-state)
      (if (max-mark-winner? game max-mark board-state)
          (- 1000 depth)
          (if (min-mark-winner? game max-mark board-state)
              (+ -1000 depth)
              (- 0 depth)))
      ab-value))))

(defn alpha-beta [game board-state max-depth]
  (let [max-mark (next-possible-mark game board-state)]
    (:index (max-value game max-mark board-state -9999 9999 0 max-depth))))

(defn determine-best-move [current-best test-move index max-node?]
  (let [best-move (if (nil? current-best)
                      (merge test-move {:index index})
                      (if (and max-node? (> (:score test-move) (:score current-best)))
                          (merge test-move {:index index})
                          (if (and (not max-node?) (< (:score test-move) (:score current-best)))
                              (merge test-move {:index index})
                              current-best)))]
    best-move))

(defn max-depth-reached? [depth max-depth]
  (and (not (nil? max-depth)) (> depth max-depth)))

(def max-value
  (memoize (fn [game max-mark board-state alpha beta depth max-depth]
    (if (or (gameover? game board-state) (max-depth-reached? depth max-depth))
        {:score (calculate-score game max-mark board-state depth alpha)}
        (loop [empty-indices (get-empty-indices (get-board game) board-state)
               best-move nil
               alpha alpha beta beta]
          (let [index (first empty-indices)
                new-board-state (set-mark-at-index game board-state index)
                other-indices (rest empty-indices)
                move (min-value game max-mark new-board-state alpha beta (inc depth) max-depth)
                best-move (determine-best-move best-move move index true)
                alpha (max alpha (:score best-move))
                cut-off? (> alpha beta)]

                (if (and (not cut-off?) (not (empty? other-indices)))
                        (recur other-indices
                               best-move
                               alpha beta)
                        (merge best-move {:alpha alpha}))))))))

(def min-value
  (memoize (fn [game max-mark board-state alpha beta depth max-depth]
    (if (or (gameover? game board-state) (max-depth-reached? depth max-depth))
        {:score (calculate-score game max-mark board-state depth beta)}
        (loop [empty-indices (get-empty-indices (get-board game) board-state)
               best-move nil
               alpha alpha beta beta]
          (let [index (first empty-indices)
                new-board-state (set-mark-at-index game board-state index)
                other-indices (rest empty-indices)
                move (max-value game max-mark new-board-state alpha beta (inc depth) max-depth)
                best-move (determine-best-move best-move move index false)
                beta (min beta (:score best-move))
                cut-off? (> alpha beta)]
                (if (and (not cut-off?) (not (empty? other-indices)))
                        (recur other-indices
                               best-move
                               alpha beta)
                        (merge best-move {:beta beta}))))))))
