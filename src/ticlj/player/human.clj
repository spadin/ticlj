(ns ticlj.player.human
  (:use [ticlj.player.protocol]
        [ticlj.game.protocol]
        [ticlj.game.protocol.board :only [valid-index?]]
        [ticlj.prompt]
        [ticlj.io.printer]))

(defrecord Human []
  Player
  (move [this game board-state]
    (let [board (get-board game)
          mark (next-possible-mark game board-state)
          index (prompt-for-move mark)]
      (if (valid-index? board board-state index)
          index
          (do
            (print-line "Invalid input, please try again.")
            (recur game board-state))))))

(def human-player (Human.))
