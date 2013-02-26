(ns ticlj.player.unbeatable
  (:require [ticlj.rules.basic :as rules]
            [ticlj.board.basic :as board]
            [ticlj.player.aplayer :as player])
  (:import [ticlj.player.aplayer APlayer]))

(declare alpha-beta)
(declare max-value)
(declare min-value)

(defrecord UnbeatableAI [mark]
  APlayer
  (move [this board]
    (alpha-beta (:mark this) board nil)))

(def max-mark-winner?
  (memoize (fn [max-mark board]
    (= (rules/winner board) max-mark))))

(def min-mark-winner?
  (memoize (fn [max-mark board]
    (let [winner (rules/winner board)]
      (and
        (not (nil? winner))
        (not= winner max-mark))))))

(def calculate-score
  (memoize (fn [max-mark board depth ab-value]
    (if (rules/gameover? board)
      (if (max-mark-winner? max-mark board)
          (- 1000 depth)
          (if (min-mark-winner? max-mark board)
              (+ -1000 depth)
              (- 0 depth)))
      ab-value))))

(defn alpha-beta [max-mark board max-depth]
  (:index (max-value max-mark board -9999 9999 0 max-depth)))

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
  (memoize (fn [max-mark board alpha beta depth max-depth]
    (if (or (rules/gameover? board) (max-depth-reached? depth max-depth))
        {:score (calculate-score max-mark board depth alpha)}
        (loop [empty-indices (board/get-empty-indices board)
               best-move nil
               alpha alpha beta beta]
          (let [index (first empty-indices)
                current-mark (board/current-mark board)
                new-board (-> board (board/set-mark-at-index current-mark
                                                   index))
                other-indices (rest empty-indices)
                move (min-value max-mark new-board alpha beta (inc depth) max-depth)
                best-move (determine-best-move best-move move index true)
                alpha (max alpha (:score best-move))
                cut-off? (> alpha beta)]
                (if (and (not cut-off?) (not (empty? other-indices)))
                        (recur other-indices
                               best-move
                               alpha beta)
                        (merge best-move {:alpha alpha}))))))))

(def min-value
  (memoize (fn [max-mark board alpha beta depth max-depth]
    (if (or (rules/gameover? board) (max-depth-reached? depth max-depth))
        {:score (calculate-score max-mark board depth beta)}
        (loop [empty-indices (board/get-empty-indices board)
               best-move nil
               alpha alpha beta beta]
          (let [index (first empty-indices)
                current-mark (board/current-mark board)
                new-board (-> board (board/set-mark-at-index current-mark
                                                   index))
                other-indices (rest empty-indices)
                move (max-value max-mark new-board alpha beta (inc depth) max-depth)
                best-move (determine-best-move best-move move index false)
                beta (min beta (:score best-move))
                cut-off? (> alpha beta)]
                (if (and (not cut-off?) (not (empty? other-indices)))
                        (recur other-indices
                               best-move
                               alpha beta)
                        (merge best-move {:beta beta}))))))))
