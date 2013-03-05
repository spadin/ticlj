(ns ticlj.game-spec
  (:use [speclj.core]
        [ticlj.game]))

(describe "ticlj.game"
  (it "returns a three-by-three-game"
    (should= ticlj.game.three_by_three.ThreeByThreeGame
             (type (get-game-of-type "ticlj.game.three-by-three/three-by-three-game"))))

  (it "returns a four-by-four-game"
    (should= ticlj.game.four_by_four.FourByFourGame
             (type (get-game-of-type "ticlj.game.four-by-four/four-by-four-game")))))
