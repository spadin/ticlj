(ns ticlj.model.player.unbeatable
  (:require [ticlj.model.rules :as rules]
            [ticlj.model.board :as board]
            [ticlj.model.player :as player])
  (:import [ticlj.model.player Player]))

(declare max-move)
(declare min-move)
(declare alpha-beta)
(defrecord UnbeatableAI [mark]
  Player
  (move [this board]
    (:position (reduce (fn [memo val]
                         (if (> (:score val) (:score memo))
                           val
                           memo))
                       (:scores (alpha-beta board/x-mark
                                            board/x-mark
                                            board 0 -9999 9999 nil))))))

(defn calculate-score [mark board]
  (if (= (rules/winner board) mark)
    1
    (if (= (rules/winner board) (rules/next-player mark))
      -1
      0)))

(defmacro dbg[x] `(let [x# ~x] (println "dbg:" '~x "=" x#) x#))

(defn alpha-beta-score [mark board depth]
  (if (= (rules/winner board) mark)
    (- 1000 depth)
    (if (= (rules/winner board) (rules/next-player mark))
      (+ -1000 depth)
      (- 0 depth))))

(defn alpha-beta [max-mark current-mark board depth alpha beta scores]
  (if (rules/gameover? board)
    {:score (alpha-beta-score max-mark board depth) :alpha alpha :beta beta :depth depth}
    (let [positions (board/get-empty-indices board)]
      (if (= max-mark current-mark) ;;max-player
        (loop [positions positions
               alpha alpha
               beta beta
               cut-off? false
               scores []]
          (let [position (first positions)
                score (:score (alpha-beta max-mark
                                  (rules/next-player current-mark)
                                  (board/set-mark-at-index current-mark position board)
                                  (inc depth)
                                  alpha beta scores))
                next-alpha (if (> score alpha) score alpha)
                next-cut-off (>= next-alpha beta)
                scores (cons {:score score
                              :position position
                              :alpha next-alpha
                              :beta beta} scores)]
            (if (and (not cut-off?) (not (empty? (rest positions))))
              (recur (rest positions)
                     next-alpha beta next-cut-off scores)
              {:score score
               :alpha next-alpha
               :beta beta
               :position position
               :depth depth
               :scores scores})))
        (loop [positions positions
               alpha alpha
               beta beta
               cut-off? false
               scores []]
          (let [position (first positions)
                score (:score (alpha-beta max-mark
                                  (rules/next-player current-mark)
                                  (board/set-mark-at-index current-mark position board)
                                  (inc depth)
                                  alpha beta scores))
                next-beta (if (< score beta) score beta)
                next-cut-off (>= alpha next-beta)
                scores (cons {:score score
                              :position position
                              :alpha alpha
                              :beta next-beta} scores)]
            (if (and (not cut-off?) (not (empty? (rest positions))))
              (recur (rest positions) alpha next-beta next-cut-off scores)
              {:score score
               :alpha alpha
               :beta next-beta
               :position position
               :depth depth
               :scores scores})))))))


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
