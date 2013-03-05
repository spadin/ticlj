(ns ticlj.game.four-by-four.ui-spec
  (:use [speclj.core]
        [ticlj.spec-helper]
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
      (should= " 0  | 1  | 2  | 3  \n----|----|----|----\n 4  | 5  | 6  | 7  \n----|----|----|----\n 8  | 9  | 10 | 11 \n----|----|----|----\n 12 | 13 | 14 | 15 \n"
               (with-out-str (print-board four-by-four-ui @sample-empty-board-state))))

    (it "should print a non-empty board"
      (should= " X  | 1  | 2  | 3  \n----|----|----|----\n 4  | O  | 6  | 7  \n----|----|----|----\n 8  | 9  | X  | 11 \n----|----|----|----\n 12 | 13 | 14 | 15 \n"
               (with-out-str (print-board four-by-four-ui @sample-non-empty-board-state)))))

  (context "/prompt-for-move"
    (it "should ask for a move"
      (should= "Player X please choose an index for your move.\n"
               (with-out-str (with-in-str "0" (prompt-for-move four-by-four-ui :X))))))

  (context "/prompt-for-choice"
    (with choices [{:name "Choice 1"
                   :value "choice-1"}
                  {:name "Choice 2"
                   :value "choice-2"}])
    (it "asks to make a choice"
      (should= "Please make a choice\n1. Choice 1\n2. Choice 2\n"
               (with-out-str (with-in-str "1" (prompt-for-choice four-by-four-ui "Please make a choice" @choices)))))

    (it "returns the value of the choice"
      (with-out-str (with-in-str "2"
        (should= "choice-2"
                 (prompt-for-choice four-by-four-ui "Please make a choice" @choices)))))

    (it "continues asking until a valid choice is made"
      (with-out-str (with-in-str (make-input `("0" "10" "1"))
        (should= "choice-1"
                 (prompt-for-choice four-by-four-ui "Please make a choice" @choices))))))

  (context "/display-gameover"
    (it "displays the game is a tie"
      (should= "Game over, tied game.\n"
               (with-out-str (display-gameover four-by-four-ui nil))))

    (it "displays that X won."
      (should= "Game over, X won.\n"
               (with-out-str (display-gameover four-by-four-ui :X))))

    (it "displays that O won."
      (should= "Game over, O won.\n"
               (with-out-str (display-gameover four-by-four-ui :O)))))

  (context "/prompt-for-game-type"
    (with game-types [{:name "3x3 Tic Tac Toe"
                       :value "four-by-four"}
                      {:name "4x4 Tic Tac Toe"
                       :value "four-by-four"}])
    (it "asks user to choose a game type"
      (should= "Please choose a game type:\n1. 3x3 Tic Tac Toe\n2. 4x4 Tic Tac Toe\n"
               (with-out-str (with-in-str "1" (prompt-for-game-type four-by-four-ui @game-types))))))

  (context "#prompt-for-player-type"
    (with player-types [{:name "Human"
                       :value "Human"}
                      {:name "Unbeatable AI"
                       :value "UnbeatableAI"}])
    (it "asks user to choose a player type"
      (should= "What type of player is this?\n1. Human\n2. Unbeatable AI\n"
               (with-out-str (with-in-str "1" (prompt-for-player-type four-by-four-ui @player-types)))))))

