(ns ticlj.game
  (:require [ticlj.model.board :as board]
            [ticlj.io.cli :as printer]))

(def winning-combinations [#{0 1 2} #{3 4 5} #{6 7 8}
                           #{0 3 6} #{1 4 7} #{2 5 8}
                           #{0 4 8} #{2 4 6}])

(defn is-winner? [mark]
  (some #(= (set (filter % (board/get-moves mark))) %) winning-combinations))

(defn winner []
  (if (is-winner? board/x-mark)
    board/x-mark
    (if (is-winner? board/o-mark)
      board/o-mark
      nil)))

(defn winner? []
  (not (nil? (winner))))

(defn gameover? []
  (if (winner?)
    true
    (if (board/full-board?)
      true
      false)))

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
    (if (gameover?)
      (if (winner?)
        (println (str "Game over, " (winner) " has won"))
        (println "Game over, tied game."))
      (do
        (request-move-recur mark)
        (play-recur (next-player mark))))))

(defn start-game []
  (play-recur))

(defn -main [& args]
  (start-game))
