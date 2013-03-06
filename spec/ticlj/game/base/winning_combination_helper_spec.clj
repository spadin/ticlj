(ns ticlj.game.base.winning-combination-helper-spec
  (:use [speclj.core]
        [ticlj.game.base.winning-combination-helper]))

(describe "ticlj.game.base.winning-combination-helper"
  (context "#horizontal-wins"
    (it "returns #{0 1 2} as a winning combination for 3x3"
      (should-contain #{0 1 2}
                      (horizontal-wins 3 2)))

    (it "returns three wins for a 3x3 (eg. 3^2) board"
      (should= 3
               (count (horizontal-wins 3 2))))

    (it "returns four wins for a 4x4 board"
      (should= 4
               (count (horizontal-wins 4 2))))

    (it "returns nine wins for a 3x3x3 (eg. 3^3) board"
      (should= 9
               (count (horizontal-wins 3 3)))))

  (context "#vertical-wins"
    (it "returns #{0 3 6} as a winning combination for 3x3"
      (should-contain #{0 3 6}
                      (vertical-wins 3 2)))

    (it "returns three wins for a 3x3 (eg. 3^2) board"
      (should= 3
               (count (vertical-wins 3 2))))

    (it "returns four wins for a 4x4 board"
      (should= 4
               (count (vertical-wins 4 2))))

    (it "returns nine wins for a 3x3x3 (eg. 3^3) board"
      (should= 9
               (count (vertical-wins 3 3)))))

  (context "#through-wins"
    (it "returns #{0 9 18} a a winning combination for 3x3x3"
      (should-contain #{0 9 18}
                      (through-wins 3 3)))

    (it "returns nine wins for a 3x3x3 board"
      (should= 9
               (count (through-wins 3 3))))))
