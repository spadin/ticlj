(ns ticlj.rules.basic
  (:require [ticlj.board.basic :as board]))

(def winning-combinations [#{0 1 2} #{3 4 5} #{6 7 8}
                           #{0 3 6} #{1 4 7} #{2 5 8}
                           #{0 4 8} #{2 4 6}])

(defn is-winner? [mark board]
  (some #(= (set (filter % (board/get-moves mark board))) %) winning-combinations))

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
