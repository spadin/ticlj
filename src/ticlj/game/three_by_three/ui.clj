(ns ticlj.game.three-by-three.ui
  (:use [ticlj.game.protocol.ui])
  (:require [ticlj.game.shared.ui :as shared-ui]
            [ticlj.io.printer :as printer]))

(defrecord ThreeByThreeUi []
  Ui
  (display-gameover [this winner] (shared-ui/display-gameover winner))
  (print-board [this board-state]
    (let [separator "---|---|---"
          index-string (fn [board-state index]
                           (let [mark (name (nth board-state index))]
                                (if (= mark "#") index mark)))
          line-string (fn [line board-state]
                          (str " "
                               (-> board-state (index-string (* line 3)))
                               " | "
                               (-> board-state (index-string (+ (* line 3) 1)))
                               " | "
                               (-> board-state (index-string (+ (* line 3) 2)))
                               " "))]
      (printer/print-line (line-string 0 board-state))
      (printer/print-line separator)
      (printer/print-line (line-string 1 board-state))
      (printer/print-line separator)
      (printer/print-line (line-string 2 board-state))
      (printer/print-line ""))))

(def three-by-three-ui (ThreeByThreeUi.))
