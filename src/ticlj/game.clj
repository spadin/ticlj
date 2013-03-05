(ns ticlj.game)

(def availble-game-types
  [{:name "3x3 Tic Tac Toe"
    :value "ticlj.game.three-by-three/three-by-three-game"}])

(defn get-game-of-type [game-type]
  @(resolve (symbol game-type)))
