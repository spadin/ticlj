(ns ticlj.model.player.unbeatable
  (:require [ticlj.model.rules :as rules]
            [ticlj.model.board :as board]
            [ticlj.model.player :as player])
  (:import [ticlj.model.player Player]))

(declare max-move)
(declare min-move)
(defrecord UnbeatableAI [mark]
  Player
  (move [this board]
    (:position (max-move (:mark this) board 0))))

(defn calculate-score [mark board]
  (if (= (rules/winner board) mark)
    1
    (if (= (rules/winner board) (rules/next-player mark))
      -1
      0)))

(defn min-move [mark board depth]
  (let [moves
        (for [position (board/get-empty-indices board)
              :let [new-board (board/set-mark-at-index mark position board)]]
          (if (rules/gameover? new-board)
            {:score (+ (- -1000 (calculate-score (rules/next-player mark) new-board)) depth)
             :position position}
            {:score (:score (max-move (rules/next-player mark) new-board (inc depth)))
             :position position}))]
    (reduce (fn [memo val]  (if (< (:score val) (:score memo)) val memo)) moves)))

(defn max-move [mark board depth]
  (let [moves
        (for [position (board/get-empty-indices board)
              :let [new-board (board/set-mark-at-index mark position board)]]
          (if (rules/gameover? new-board)
            {:score (- 1000 (calculate-score mark new-board) depth)
             :position position}
            {:score (:score (min-move (rules/next-player mark) new-board (inc depth)))
             :position position}))]
    (reduce (fn [memo val]  (if (> (:score val) (:score memo)) val memo)) moves)))
