(ns ticlj.rules.basic
  (:use [ticlj.rules.game-type :only (*game-type*)])
  (:require [ticlj.board.basic :as board]
            [ticlj.rules.game-type :as game-type]))

(defmulti multi-winning-combinations :game-type)
(defmethod multi-winning-combinations game-type/basic [this]
  [#{0 1 2} #{3 4 5} #{6 7 8}
   #{0 3 6} #{1 4 7} #{2 5 8}
   #{0 4 8} #{2 4 6}])

(defn winning-combinations []
  (multi-winning-combinations {:game-type *game-type*}))

(defn is-winner? [mark board]
  (some #(= (set (filter % (board/get-moves mark board))) %) (winning-combinations)))

(defn winner [board]
  (if (is-winner? board/x-mark board)
    board/x-mark
    (if (is-winner? board/o-mark board)
      board/o-mark
      nil)))

(defn winner? [board]
  (not (nil? (winner board))))

(defn gameover? [board]
  (if (winner? board)
    true
    (if (board/full-board? board)
      true
      false)))

(defn next-player [mark]
  (if (= board/x-mark mark) board/o-mark board/x-mark))
