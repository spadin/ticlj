(ns ticlj.game
  (:require [ticlj.model.board :as board]
            [ticlj.io.cli :as printer]))

(defn next-player [mark]
  (if (= board/x-mark mark) board/o-mark board/x-mark))

(defn request-move-recur [mark]
    (try (board/set-mark-at-index mark (printer/prompt-player mark))
      (catch Exception e
        (println "Invalid move, please try again.")
        (request-move-recur mark))))


(defn play-recur
  ([] (trampoline play-recur board/x-mark))
  ([mark]
    (printer/print-board)
    (request-move-recur mark)
    (play-recur (next-player mark))))


(defn start-game []
  (play-recur))

(defn -main [& args]
  (start-game))
