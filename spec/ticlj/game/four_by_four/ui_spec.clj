(ns ticlj.game.four-by-four.ui-spec
  (:use [speclj.core]
        [ticlj.game.protocol.ui]
        [ticlj.game.four-by-four.ui]))

(describe "ticlj.game.four-by-four.ui"
  (with sample-empty-board-state [:# :# :# :#
                                  :# :# :# :#
                                  :# :# :# :#
                                  :# :# :# :#])

  (with sample-non-empty-board-state [:X :# :# :#
                                      :# :O :# :#
                                      :# :# :X :#
                                      :# :# :# :#])
  (context "/print-board"
    (it "should print an empty board"
      (should= " 0  | 1  | 2  | 3  \n----|----|----|----\n 4  | 5  | 6  | 7  \n----|----|----|----\n 8  | 9  | 10 | 11 \n----|----|----|----\n 12 | 13 | 14 | 15 \n\n"
               (with-out-str (print-board four-by-four-ui @sample-empty-board-state))))

    (it "should print a non-empty board"
      (should= " X  | 1  | 2  | 3  \n----|----|----|----\n 4  | O  | 6  | 7  \n----|----|----|----\n 8  | 9  | X  | 11 \n----|----|----|----\n 12 | 13 | 14 | 15 \n\n"
               (with-out-str (print-board four-by-four-ui @sample-non-empty-board-state)))))

  (context "/display-gameover"
    (it "displays the game is a tie"
      (should= "Game over, tied game.\n\n"
               (with-out-str (display-gameover four-by-four-ui nil))))

    (it "displays that X won."
      (should= "Game over, X won.\n\n"
               (with-out-str (display-gameover four-by-four-ui :X))))

    (it "displays that O won."
      (should= "Game over, O won.\n\n"
               (with-out-str (display-gameover four-by-four-ui :O))))))
