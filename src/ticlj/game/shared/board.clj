(ns ticlj.game.shared.board)

(declare ^:dynamic *board-size*)
(declare ^:dynamic *num-boards*)

(defn empty-board-state [board-size num-boards]
  (vec (repeat (* board-size num-boards) :#)))

(defn get-mark-at-index [board-state index]
  (nth board-state index))

(defn valid-index? [board-state index]
 (and
   (and
     (>= index 0)
     (< index (count board-state))
   (= :# (get-mark-at-index board-state index)))))

(defn get-move-indices [board-state mark]
 (set (remove nil? (map-indexed (fn [idx val] (if (= val mark) idx nil)) board-state))))

(defn next-possible-mark [board-state]
 (let [num-moves (+ (count (get-move-indices board-state :X))
                    (count (get-move-indices board-state :O)))]
   (if (even? num-moves) :X :O)))

(defn set-mark-at-index [board-state index]
 (let [mark (next-possible-mark board-state)]
   (if (valid-index? board-state index)
     (vec (concat (take index board-state)
                  [mark]
                  (rest (drop index board-state))))
     board-state)))

(defn get-empty-indices [board-state]
     (get-move-indices board-state :#))

(defn full-board? [board-state]
     (empty? (get-empty-indices board-state)))
