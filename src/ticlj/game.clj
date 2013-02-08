(ns ticlj.game
  (:require [ticlj.model.board :as board]
            [ticlj.io.cli :as printer]
            [ticlj.model.rules :as rules]))

(defn request-human-move [mark board]
    (try (board/set-mark-at-index mark (printer/prompt-player mark) board)
      (catch Exception e
        (println (str "Invalid move, please try again." e))
        (request-human-move mark board))))

(defn play
  ([] (play board/x-mark board/empty-board))
  ([mark board]
    (printer/print-board board)
    (if (rules/gameover? board)
      (if (rules/winner? board)
        (println (str "Game over, " (rules/winner board) " has won"))
        (println "Game over, tied game."))
      (do
        (recur (rules/next-player mark) (request-human-move mark board))))))

(defn start-game []
  (play))

(defn -main [& args]
  (start-game))
