(ns ticlj.game
  (:require [ticlj.model.board :as board]
            [ticlj.io.cli :as printer]
            [ticlj.model.rules :as rules]
            [ticlj.model.player :as player]
            [ticlj.model.player.human :as human]
            [ticlj.model.player.easy :as easy]
            [ticlj.model.player.medium :as medium]
            [ticlj.model.player.unbeatable :as unbeatable])
  (:import [ticlj.model.player.human Human]
           [ticlj.model.player.easy EasyAI]
           [ticlj.model.player.medium MediumAI]
           [ticlj.model.player.unbeatable UnbeatableAI]))

(defn build-player [player-1 player-2 mark]
  (if (= mark board/x-mark)
    (if (= player-1 1)
        (Human. mark)
        (if (= player-1 2)
            (UnbeatableAI. mark)
            (if (= player-1 3)
                (EasyAI. mark)
                (MediumAI. mark))))
    (if (= player-2 1)
        (Human. mark)
        (if (= player-2 2)
            (UnbeatableAI. mark)
            (if (= player-2 3)
                (EasyAI. mark)
                (MediumAI. mark))))))


(defn play
  ([game-type player-1 player-2] (play board/x-mark board/empty-board player-1 player-2))
  ([mark board player-1 player-2]
    (printer/print-board board)
    (if (rules/gameover? board)
      (printer/print-gameover (rules/winner board))
      (recur (rules/next-player mark)
             (-> board (board/set-mark-at-index mark (player/move (build-player player-1 player-2 mark) board)))
              player-1 player-2))))

(defn start-game []
  (let [game-type (printer/prompt-game-type)
        player-1 (printer/prompt-player-type 1)
        player-2 (printer/prompt-player-type 2)]
    (play game-type player-1 player-2)))

(defn -main [& args]
  (start-game))
