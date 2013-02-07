(ns ticlj.game
  (:require [ticlj.model.board :as board]
            [ticlj.io.cli :as printer]
            [ticlj.model.rules :as rules]))

(defn request-move-recur [mark]
    (try (board/set-mark-at-index mark (printer/prompt-player mark))
      (catch Exception e
        (println "Invalid move, please try again.")
        (request-move-recur mark))))

(defn play-recur
  ([] (trampoline play-recur board/x-mark))
  ([mark]
    (printer/print-board)
    (if (rules/gameover?)
      (if (rules/winner?)
        (println (str "Game over, " (rules/winner) " has won"))
        (println "Game over, tied game."))
      (do
        (request-move-recur mark)
        (play-recur (rules/next-player mark))))))

(defn start-game []
  (play-recur))

(defn -main [& args]
  (start-game))
