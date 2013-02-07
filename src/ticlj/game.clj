(ns ticlj.game
  (:require [ticlj.model.board :as board]
            [ticlj.io.cli :as printer]
            [ticlj.model.rules :as rules]))

(defn request-human-move [mark]
    (try (board/set-mark-at-index mark (printer/prompt-player mark))
      (catch Exception e
        (println "Invalid move, please try again.")
        (request-human-move mark))))

(defn play
  ([] (play board/x-mark))
  ([mark]
    (printer/print-board)
    (if (rules/gameover?)
      (if (rules/winner?)
        (println (str "Game over, " (rules/winner) " has won"))
        (println "Game over, tied game."))
      (do
        (request-human-move mark)
        (recur (rules/next-player mark))))))

(defn start-game []
  (play))

(defn -main [& args]
  (start-game))
