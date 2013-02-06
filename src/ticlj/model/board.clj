(ns ticlj.model.board)

(def x-mark "x")
(def o-mark "o")
(def nomark " ")

(def allowed-marks [x-mark o-mark])

(def empty-board [nomark nomark nomark
                  nomark nomark nomark
                  nomark nomark nomark])

(def board (ref empty-board))

(defn allowed-mark? [mark] (not (nil? (some #{mark} allowed-marks))))

(defn allowed-index? [index]
  (and (>= index 0) (<= index 9)))

(defn reset-board []
  (dosync
    (ref-set board empty-board)))

(defn set-mark-at-index [mark index]
  (if (allowed-index? index)
    (if (allowed-mark? mark)
     (dosync
        (ref-set board (concat (take index @board)
                               [mark]
                               (rest (drop index @board)))))
      (throw (Exception. "Invalid mark")))
    (throw (Exception. "Invalid index"))))

(defn mark-at-index [index]
  (nth @board index))
