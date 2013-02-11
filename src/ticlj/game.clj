(ns ticlj.game
  (:require [ticlj.model.board :as board]
            [ticlj.io.cli :as printer]
            [ticlj.model.rules :as rules]
            [ticlj.model.player :as player])
  (:import [ticlj.model.player Human UnbeatableAI]))

(defn build-player [player-1 player-2 mark]
  (if (= mark board/x-mark)
    (if (= player-1 1)
      (Human. mark)
      (UnbeatableAI. mark))
    (if (= player-2 1)
      (Human. mark)
      (UnbeatableAI. mark))))


(defn play
  ([player-1 player-2] (play board/x-mark board/empty-board player-1 player-2))
  ([mark board player-1 player-2]
    (printer/print-board board)
    (if (rules/gameover? board)
      (if (rules/winner? board)
        (println (str "Game over, " (rules/winner board) " has won"))
        (println "Game over, tied game."))
      (recur (rules/next-player mark)
             (board/set-mark-at-index mark (player/move (build-player player-1 player-2 mark) board) board)
              player-1 player-2))))

(defn start-game []
  (let [player-1 (printer/prompt-player-type 1)
        player-2 (printer/prompt-player-type 2)]
    (play player-1 player-2)))

(defn -main [& args]
  (start-game))
