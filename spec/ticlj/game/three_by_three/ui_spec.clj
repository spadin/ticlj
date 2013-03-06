(ns ticlj.game.three-by-three.ui-spec
  (:use [speclj.core]
        [ticlj.game.protocol.ui]
        [ticlj.game.three-by-three.ui]))

(describe "ticlj.game.three-by-three.ui"
  (with sample-empty-board-state [:# :# :#
                                  :# :# :#
                                  :# :# :#])

  (with sample-non-empty-board-state [:X :# :#
                                      :# :O :#
                                      :# :# :X])
  (context "/print-board"
    (it "should print an empty board"
      (should= " 0 | 1 | 2 \n---|---|---\n 3 | 4 | 5 \n---|---|---\n 6 | 7 | 8 \n"
               (with-out-str (print-board three-by-three-ui @sample-empty-board-state))))

    (it "should print a non-empty board"
      (should= " X | 1 | 2 \n---|---|---\n 3 | O | 5 \n---|---|---\n 6 | 7 | X \n"
               (with-out-str (print-board three-by-three-ui @sample-non-empty-board-state)))))

  (context "/display-gameover"
    (it "displays the game is a tie"
      (should= "Game over, tied game.\n"
               (with-out-str (display-gameover three-by-three-ui nil))))

    (it "displays that X won."
      (should= "Game over, X won.\n"
               (with-out-str (display-gameover three-by-three-ui :X))))

    (it "displays that O won."
      (should= "Game over, O won.\n"
               (with-out-str (display-gameover three-by-three-ui :O))))))
