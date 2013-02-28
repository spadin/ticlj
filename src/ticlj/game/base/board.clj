(ns ticlj.game.base.board)

(defprotocol Board
  (size [this])
  (num-boards [this])
  (empty-board [this])
  (valid-index? [this board index])
  (get-move-indices [this board mark])
  (get-empty-indices [this board])
  (full-board? [this board])
  (next-possible-mark [this board])
  (set-mark-at-index [this board index])
  (get-mark-at-index [this board index]))

(defrecord BaseBoard [])
(def base-board (BaseBoard.))

(def board-defaults
  {:size
   (fn [this]
     9)

   :num-boards
   (fn [this]
     1)

   :empty-board
   (fn [this]
     (vec (repeat (* (size this) (num-boards this)) :#)))

   :valid-index?
   (fn [this board-state index]
     (and
       (and
         (>= index 0)
         (< index (count (empty-board this))))
       (= :# (get-mark-at-index this board-state index))))

   :get-move-indices
   (fn [this board-state mark]
     (set (remove nil? (map-indexed (fn [idx val] (if (= val mark) idx nil)) board-state))))

   :get-empty-indices
   (fn [this board-state]
     (get-move-indices this board-state :#))

   :full-board?
   (fn [this board-state]
     (empty? (get-empty-indices this board-state)))

   :next-possible-mark
   (fn [this board-state]
     (let [num-moves (+ (count (get-move-indices this board-state :X))
                        (count (get-move-indices this board-state :O)))]
       (if (even? num-moves) :X :O)))

   :set-mark-at-index
   (fn [this board-state index]
     (let [mark (next-possible-mark this board-state)]
       (if (valid-index? this board-state index)
         (vec (concat (take index board-state)
                      [mark]
                      (rest (drop index board-state))))
         board-state)))

   :get-mark-at-index
   (fn [this board-state index]
     (nth board-state index))})

(extend BaseBoard
  Board
  board-defaults)
