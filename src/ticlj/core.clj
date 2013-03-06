(ns ticlj.core
  (:use [ticlj.game]
        [ticlj.player]
        [ticlj.prompt]
        [ticlj.game.protocol]
        [ticlj.player.protocol]))

(defn play [game players board-state]
    (print-board game board-state)
    (let [mark (next-possible-mark game board-state)]
      (if (gameover? game board-state)
        (display-gameover game (winner game board-state))
        (recur game players (set-mark-at-index
                              game
                              board-state
                              (move (get players mark) game board-state))))))

(defn start-game []
  (let [game-type (prompt-for-game-type available-game-types)
        player-x (prompt-for-player-type available-player-types)
        player-o (prompt-for-player-type available-player-types)
        game (get-game-of-type game-type)
        players {:X (get-player-of-type player-x)
                 :O (get-player-of-type player-o)}]
    (play game players (empty-board-state game))))

(defn -main [& args]
  (start-game))
