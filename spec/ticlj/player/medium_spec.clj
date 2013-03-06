(ns ticlj.player.medium-spec
  (:use [speclj.core]
        [ticlj.player.protocol]
        [ticlj.player.medium]))

(describe "ticlj.player.medium"
  (with sample-game ticlj.game.three-by-three/three-by-three-game)

  (it "returns a move to win game"
    (let [board-state [:X :O :X
                       :# :O :#
                       :O :# :X]]
      (should= 5
               (move medium-ai-player @sample-game board-state)))))
