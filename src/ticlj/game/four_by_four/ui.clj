(ns ticlj.game.four-by-four.ui
  (:use [ticlj.game.protocol.ui])
  (:require [ticlj.game.shared.ui :as shared-ui]
            [ticlj.io.printer :as printer]))

(defrecord FourByFour []
  Ui
  (display-gameover [this winner] (shared-ui/display-gameover winner))
  (print-board [this board-state]
    (let [separator "----|----|----|----"
          index-string (fn [board-state index]
                           (let [mark (name (nth board-state index))]
                              (format "%-2s"  (if (= mark "#") index mark))))
          line-string (fn [line board-state]
                          (str " "
                               (-> board-state (index-string (* line 4)))
                               " | "
                               (-> board-state (index-string (+ (* line 4) 1)))
                               " | "
                               (-> board-state (index-string (+ (* line 4) 2)))
                               " | "
                               (-> board-state (index-string (+ (* line 4) 3)))
                               " "))]
      (printer/print-line (line-string 0 board-state))
      (printer/print-line separator)
      (printer/print-line (line-string 1 board-state))
      (printer/print-line separator)
      (printer/print-line (line-string 2 board-state))
      (printer/print-line separator)
      (printer/print-line (line-string 3 board-state))
      (printer/print-line ""))))

(def four-by-four-ui (FourByFour.))
