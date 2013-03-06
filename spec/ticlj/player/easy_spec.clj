(ns ticlj.player.easy-spec
  (:use [speclj.core]
        [ticlj.spec-helper]
        [ticlj.game.protocol]
        [ticlj.player.protocol]
        [ticlj.player.easy]))

(describe "ticlj.player.easy"
  (with sample-game ticlj.game.three-by-three/three-by-three-game)

  (it "makes the first available move in an empty board"
    (let [board-state [:# :# :#
                       :# :# :#
                       :# :# :#]]
      (should= 0
               (make-move (get-board @sample-game) board-state))))

  (it "make the first available move in a non-empty board"
    (let [board-state [:X :X :#
                       :# :# :#
                       :# :# :#]]
      (should= 2
               (make-move (get-board @sample-game) board-state))))

  (it "makes the first available move via the player/move function"
    (let [board-state [:X :# :#
                       :# :# :#
                       :# :# :#]]
      (should= 1
               (move easy-ai-player @sample-game board-state)))))
