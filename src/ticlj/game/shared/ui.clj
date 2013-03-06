(ns ticlj.game.shared.ui
  (:require [ticlj.io.printer :as printer]
            [ticlj.io.reader :as reader]))

(defn display-gameover [winner]
  (if (nil? winner)
      (printer/print-line "Game over, tied game.")
      (printer/print-line (str "Game over, " (name winner) " won."))))
