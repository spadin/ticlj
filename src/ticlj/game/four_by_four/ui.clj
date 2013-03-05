(ns ticlj.game.four-by-four.ui
  (:use [ticlj.game.protocol.ui])
  (:require [ticlj.game.shared.ui :as shared-ui]
            [ticlj.game.shared.printer :as printer]))

(defrecord FourByFour []
  Ui
  (prompt-for-move [this mark] (shared-ui/prompt-for-move mark))
  (prompt-for-choice [this message choices] (shared-ui/prompt-for-choice message choices))
  (prompt-for-game-type [this game-types] (shared-ui/prompt-for-game-type game-types))
  (prompt-for-player-type [this player-types] (shared-ui/prompt-for-player-type player-types))
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
      (println (line-string 0 board-state))
      (println separator)
      (println (line-string 1 board-state))
      (println separator)
      (println (line-string 2 board-state))
      (println separator)
      (println (line-string 3 board-state)))))

(def four-by-four-ui (FourByFour.))
