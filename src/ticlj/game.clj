(ns ticlj.game
  (:require [ticlj.model.board :as board]
            [ticlj.io.cli :as printer]))

(defn next-player [mark]
  (if (= board/x-mark mark) board/o-mark board/x-mark))

(defn play-recur
  ([] (play-recur board/x-mark))
  ([mark]
    (printer/print-board)
    (board/set-mark-at-index mark (printer/prompt-player mark))
    (recur (next-player mark))))

(defn start-game []
  (play-recur)
  true)

(defn -main [& args]
  (start-game))
