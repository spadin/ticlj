(ns ticlj.game
  (require [ticlj.game.three-by-three]
           [ticlj.game.four-by-four]))

(def available-game-types
  [{:name "3x3 Tic Tac Toe"
    :value "ticlj.game.three-by-three/three-by-three-game"}
   {:name "4x4 Tic Tac Toe"
    :value "ticlj.game.four-by-four/four-by-four-game"}])

(defn get-game-of-type [game-type]
  @(resolve (symbol game-type)))
