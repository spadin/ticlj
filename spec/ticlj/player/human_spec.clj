(ns ticlj.player.human-spec
  (:use [speclj.core]
        [ticlj.spec-helper]
        [ticlj.player.protocol]
        [ticlj.player.human]))

(describe "ticlj.player.human"
  (with sample-game ticlj.game.three-by-three/three-by-three-game)
  (with sample-empty-board-state [:# :# :#
                                  :# :# :#
                                  :# :# :#])

  (it "returns an index as the move"
    (with-out-str
      (should= 0
               (with-in-str "0"
                 (move human-player @sample-game @sample-empty-board-state)))))

  (it "requests a move until the move is valid"
    (should (.contains (with-out-str (with-in-str (make-input '("x" "u" 0))
                         (move human-player @sample-game @sample-empty-board-state)))
                       "Invalid input, please try again."))))
