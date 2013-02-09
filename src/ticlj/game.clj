(ns ticlj.game
  (:use [ticlj.model.player])
  (:require [ticlj.model.board :as board]
            [ticlj.io.cli :as printer]
            [ticlj.model.rules :as rules])
  (:import [ticlj.model.player Human]))

(defn play
  ([] (play board/x-mark board/empty-board))
  ([mark board]
    (printer/print-board board)
    (if (rules/gameover? board)
      (if (rules/winner? board)
        (println (str "Game over, " (rules/winner board) " has won"))
        (println "Game over, tied game."))
        (recur (rules/next-player mark) (board/set-mark-at-index mark (move (Human. mark) board) board)))))

(defn start-game []
  (play))

(defn -main [& args]
  (start-game))
