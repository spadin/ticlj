(ns ticlj.model.player.unbeatable
  (:require [ticlj.model.rules :as rules]
            [ticlj.model.board :as board]
            [ticlj.model.player :as player])
  (:import [ticlj.model.player Player]))

(declare alpha-beta)
(declare max-value)
(declare min-value)

(defrecord UnbeatableAI [mark]
  Player
  (move [this board]
    (alpha-beta (:mark this) board)))

(defmacro dbg[x] `(let [x# ~x] (println "dbg:" '~x "=" x#) x#))

(defn max-mark-winner? [max-mark board]
  (= (rules/winner board) max-mark))

(defn min-mark-winner? [max-mark board]
  (= (rules/winner board) (rules/next-player max-mark)))

(defn calculate-score [max-mark board depth]
  (if (max-mark-winner? max-mark board)
      (- 1000 depth)
      (if (min-mark-winner? max-mark board)
          (+ -1000 depth)
          (- 0 depth))))

(defn alpha-beta [max-mark board]
  (:index (max-value max-mark max-mark board -9999 9999 0)))

(defn determine-best-move [current-best test-move index max-node?]
  (let [best-move (if (nil? current-best)
                      (merge test-move {:index index})
                      (if (and max-node? (> (:score test-move) (:score current-best)))
                          (merge test-move {:index index})
                          (if (and (not max-node?) (< (:score test-move) (:score current-best)))
                              (merge test-move {:index index})
                              current-best)))]
    best-move))

(defn max-value [max-mark current-mark board alpha beta depth]
  (if (rules/gameover? board)
      {:score (calculate-score max-mark board depth)}
      (loop [empty-indices (board/get-empty-indices board)
             best-move nil
             alpha alpha
             beta beta
             cut-off? false]
        (let [index (first empty-indices)
              next-mark (rules/next-player current-mark)
              new-board (board/set-mark-at-index current-mark
                                                 index
                                                 board)
              other-indices (rest empty-indices)
              move (min-value max-mark next-mark new-board alpha beta (inc depth))
              next-best-move (determine-best-move best-move move index true)
              next-alpha (max alpha (:score next-best-move))
              next-beta beta
              next-cut-off? (> next-alpha next-beta)]
              (if-not (and (not cut-off?) (empty? other-indices))
                      (recur other-indices
                             next-best-move
                             next-alpha
                             next-beta
                             cut-off?)
                             next-best-move)))))

(defn min-value [max-mark current-mark board alpha beta depth]
  (if (rules/gameover? board)
      {:score (calculate-score max-mark board depth)}
      (loop [empty-indices (board/get-empty-indices board)
             best-move nil
             alpha alpha
             beta beta
             cut-off? false]
        (let [index (first empty-indices)
              next-mark (rules/next-player current-mark)
              new-board (board/set-mark-at-index current-mark
                                                 index
                                                 board)
              other-indices (rest empty-indices)
              move (max-value max-mark next-mark new-board alpha beta (inc depth))
              next-best-move (determine-best-move best-move move index false)
              next-alpha alpha
              next-beta (min beta (:score next-best-move))
              next-cut-off? (> next-alpha next-beta)]
              (if-not (and (not cut-off?) (empty? other-indices))
                      (recur other-indices
                             next-best-move
                             next-alpha
                             next-beta
                             cut-off?)
                             next-best-move)))))
