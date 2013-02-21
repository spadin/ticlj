(ns ticlj.rules.game-type-spec
  (:use [speclj.core]
        [ticlj.spec-helper]
        [ticlj.rules.game-type]))

(describe "ticlj.rules.game-type"
  (it "defines the default game-type as basic"
    (should= basic
             *game-type*))

  (it "allows re-binding of game-type"
    (binding [*game-type* four-by-four]
      (should= four-by-four
               ticlj.rules.game-type/*game-type*))))
