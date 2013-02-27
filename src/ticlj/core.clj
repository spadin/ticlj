(ns ticlj.core
  (:use [ticlj.rules.game-type :only (*game-type*)])
  (:require [ticlj.board.basic :as board]
            [ticlj.printer.basic :as printer]
            [ticlj.rules.basic :as rules]
            [ticlj.player.aplayer :as player]
            [ticlj.player.human :as human]
            [ticlj.player.easy :as easy]
            [ticlj.player.medium :as medium]
            [ticlj.player.unbeatable :as unbeatable])
  (:import [ticlj.player.human Human]
           [ticlj.player.easy EasyAI]
           [ticlj.player.medium MediumAI]
           [ticlj.player.unbeatable UnbeatableAI]))

(defn build-player [player-1 player-2 mark]
  (if (= mark board/x-mark)
    (if (= player-1 1)
        (Human. mark)
        (if (= player-1 2)
            (UnbeatableAI. mark)
            (if (= player-1 3)
                (MediumAI. mark)
                (EasyAI. mark))))
    (if (= player-2 1)
        (Human. mark)
        (if (= player-2 2)
            (UnbeatableAI. mark)
            (if (= player-2 3)
                (MediumAI. mark)
                (EasyAI. mark))))))


(defn play
  ([player-1 player-2] (play (board/empty-board) player-1 player-2))
  ([board player-1 player-2]
    (printer/print-board board)
    (if (rules/gameover? board)
      (printer/print-gameover (rules/winner board))
      (recur (-> board
               (board/set-mark-at-index (board/current-mark board)
                                        (player/move (build-player player-1 player-2 (board/current-mark board)) board)))
              player-1 player-2))))

(defn start-game []
  (binding [*game-type* (printer/prompt-game-type)]
    (let [player-1 (printer/prompt-player-type 1)
          player-2 (printer/prompt-player-type 2)]
      (play player-1 player-2))))

(defn -main [& args]
  (start-game))
