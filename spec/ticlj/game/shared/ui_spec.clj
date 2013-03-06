(ns ticlj.game.shared.ui-spec
  (:use [speclj.core]
        [ticlj.spec-helper]
        [ticlj.game.shared.ui]))

(describe "ticlj.game.shared.ui"
  (context "/display-gameover"
    (it "displays the game is a tie"
      (should= "Game over, tied game.\n\n"
               (with-out-str (display-gameover nil))))

    (it "displays that X won."
      (should= "Game over, X won.\n\n"
               (with-out-str (display-gameover :X))))

    (it "displays that O won."
      (should= "Game over, O won.\n\n"
               (with-out-str (display-gameover :O))))))
