(ns ticlj.model.rules
  (:require [ticlj.model.board :as board]))

(def winning-combinations [#{0 1 2} #{3 4 5} #{6 7 8}
                           #{0 3 6} #{1 4 7} #{2 5 8}
                           #{0 4 8} #{2 4 6}])

(defn is-winner? [mark]
  (some #(= (set (filter % (board/get-moves mark))) %) winning-combinations))

(defn winner []
  (if (is-winner? board/x-mark)
    board/x-mark
    (if (is-winner? board/o-mark)
      board/o-mark
      nil)))

(defn winner? []
  (not (nil? (winner))))

(defn gameover? []
  (if (winner?)
    true
    (if (board/full-board?)
      true
      false)))

(defn next-player [mark]
  (if (= board/x-mark mark) board/o-mark board/x-mark))
