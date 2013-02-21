(ns ticlj.board.basic)

(def x-mark "x")
(def o-mark "o")
(def nomark " ")

(def allowed-marks [x-mark o-mark])

(def empty-board [nomark nomark nomark
                  nomark nomark nomark
                  nomark nomark nomark])

(defn allowed-mark? [mark] (not (nil? (some #{mark} allowed-marks))))

(defn allowed-index? [index]
  (and (>= index 0) (<= index 8)))

(defn empty-index? [index board]
  (= nomark (nth board index)))

(defn full-board? [board]
  (nil? (some #(= nomark %) board)))

(defn validate-mark-at-index [mark index board]
  (if (allowed-index? index)
    (if (allowed-mark? mark)
      (if (empty-index? index board)
        index
        (throw (Exception. "Spot taken")))
      (throw (Exception. "Invalid mark")))
    (throw (Exception. "Invalid index"))))

(defn set-mark-at-index [board mark index]
  (if (validate-mark-at-index mark index board)
    (vec (concat (take index board)
            [mark]
            (rest (drop index board))))))

(defn mark-at-index [index board]
  (nth board index))

(defn get-moves [mark board]
  (set (remove nil? (map-indexed (fn [idx val] (if (= val mark) idx nil)) board))))

(defn get-empty-indices [board]
  (vec (get-moves nomark board)))