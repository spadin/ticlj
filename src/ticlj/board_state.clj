(ns ticlj.board-state)

(def x-mark "x")
(def o-mark "o")
(def nomark " ")

(defn empty-board [] [nomark nomark nomark
                      nomark nomark nomark
                      nomark nomark nomark])

(def ^:dynamic board (ref (empty-board)))

(defn reset-board [] 
  (dosync (ref-set board (empty-board))))

(defn set-mark-at-index [mark index]
  (dosync
    (ref-set board (concat (take index @board) [mark] (rest (drop index @board))))))

(defn mark-at-index [index]
  (nth @board index))
