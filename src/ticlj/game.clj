(ns ticlj.game
  (:require [ticlj.model.board :as board]
            [ticlj.io.cli :as printer]))

(def winning-combinations [#{0 1 2} #{3 4 5} #{6 7 8}
                           #{0 3 6} #{1 4 7} #{2 5 8}
                           #{0 4 8} #{2 4 6}])

(defn winner []
  (if (some #(= (set (filter % (board/get-moves board/x-mark))) %) winning-combinations)
    board/x-mark
    (if (some #(= (set (filter % (board/get-moves board/o-mark))) %) winning-combinations)
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
    (request-move-recur mark)
    (play-recur (next-player mark))))


(defn start-game []
  (play-recur))

(defn -main [& args]
  (start-game))
