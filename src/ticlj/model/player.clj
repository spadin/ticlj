(ns ticlj.model.player
  (:require [ticlj.io.cli :as printer]
            [ticlj.model.rules :as rules]
            [ticlj.model.board :as board]))

(defprotocol Player
  (move [this board]))

(defrecord Human [mark]
  Player
  (move [this board]
    (try (board/validate-mark-at-index (:mark this) (printer/prompt-player (:mark this)) board)
      (catch Exception e
        (println (str "Invalid move, please try again." e))
        (move this board)))))

(declare max-move)
(declare min-move)
(defrecord UnbeatableAI [mark]
  Player
  (move [this board]
    (:position (max-move (:mark this) board))))

(defn calculate-score [mark board]
  (if (= (rules/winner board) mark)
    1
    (if (= (rules/winner board) (rules/next-player mark))
      -1
      0)))

(defn min-move [mark board]
  (let [moves
        (for [position (board/get-empty-indices board)
              :let [new-board (board/set-mark-at-index mark position board)]]
          (if (rules/gameover? new-board)
            {:score (calculate-score (rules/next-player mark) new-board)
             :position position}
            {:score (:score (max-move (rules/next-player mark) new-board))
             :position position}))]
    (reduce (fn [memo val]  (if (< (:score val) (:score memo)) val memo)) moves)))

(defn max-move [mark board]
  (let [moves
        (for [position (board/get-empty-indices board)
              :let [new-board (board/set-mark-at-index mark position board)]]
          (if (rules/gameover? new-board)
            {:score (calculate-score mark new-board)
             :position position}
            {:score (:score (min-move (rules/next-player mark) new-board))
             :position position}))]
    (reduce (fn [memo val]  (if (> (:score val) (:score memo)) val memo)) moves)))
