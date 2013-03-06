(ns ticlj.game.base.ui-spec
  (:use [speclj.core]
        [ticlj.spec-helper]
        [ticlj.game.base.ui]))

(describe "ticlj.game.base.ui"
  (context "#prompt-for-move"
    (it "should ask for a move"
      (should= "Player X please choose an index for your move.\n"
               (with-out-str (with-in-str "0" (prompt-for-move base-ui :X))))))

  (context "#prompt-for-choice"
    (with choices [{:name "Choice 1"
                   :value "choice-1"}
                  {:name "Choice 2"
                   :value "choice-2"}])
    (it "asks to make a choice"
      (should= "Please make a choice\n1. Choice 1\n2. Choice 2\n"
               (with-out-str (with-in-str "1" (prompt-for-choice base-ui "Please make a choice" @choices)))))

    (it "returns the value of the choice"
      (with-out-str (with-in-str "2"
        (should= "choice-2"
                 (prompt-for-choice base-ui "Please make a choice" @choices)))))

    (it "continues asking until a valid choice is made"
      (with-out-str (with-in-str (make-input `("0" "10" "1"))
        (should= "choice-1"
                 (prompt-for-choice base-ui "Please make a choice" @choices))))))

  (context "#display-gameover"
    (it "displays the game is a tie"
      (should= "Game over, tied game.\n"
               (with-out-str (display-gameover base-ui nil))))

    (it "displays that X won."
      (should= "Game over, X won.\n"
               (with-out-str (display-gameover base-ui :X))))

    (it "displays that O won."
      (should= "Game over, O won.\n"
               (with-out-str (display-gameover base-ui :O)))))

  (context "#prompt-for-game-type"
    (with game-types [{:name "3x3 Tic Tac Toe"
                       :value "three-by-three"}
                      {:name "4x4 Tic Tac Toe"
                       :value "four-by-four"}])
    (it "asks user to choose a game type"
      (should= "Please choose a game type:\n1. 3x3 Tic Tac Toe\n2. 4x4 Tic Tac Toe\n"
               (with-out-str (with-in-str "1" (prompt-for-game-type base-ui @game-types))))))

  (context "#prompt-for-player-type"
    (with player-types [{:name "Human"
                       :value "Human"}
                      {:name "Unbeatable AI"
                       :value "UnbeatableAI"}])
    (it "asks user to choose a player type"
      (should= "What type of player is this?\n1. Human\n2. Unbeatable AI\n"
               (with-out-str (with-in-str "1" (prompt-for-player-type base-ui @player-types)))))))

;(describe "ticlj.printer.basic"
  ;(it "prompts player x for a move"
    ;(should= "Player x choose the index of an empty spot for your next move\n"
             ;(with-out-str (with-in-str "0" (prompt-player board/x-mark)))))

  ;(it "prompts player o for a move"
    ;(should= "Player o choose the index of an empty spot for your next move\n"
             ;(with-out-str (with-in-str "0" (prompt-player board/o-mark)))))

  ;(it "rejects invalid input until a valid input is entered"
    ;(with-out-str (with-in-str (make-input '("bad input" "0"))
      ;(should= 0 (prompt-integer "enter an integer")))))

  ;(it "gets user move as an Integer"
    ;(with-out-str (with-in-str "1"
      ;(should= 1 (prompt-integer "enter an interger")))))

  ;(it "prompts player 1 to choose type of player."
    ;(should= "What type of player is player 1?\n1. Human\n2. Unbeatable AI\n3. Medium AI\n4. Easy AI\n"
             ;(with-out-str (with-in-str "1" (prompt-player-type 1)))))

  ;(it "returns Human string when user chooses 1."
    ;(with-out-str (with-in-str "1"
                    ;(should= "Human"
                             ;(prompt-player-type 1)))))

  ;(it "prints a gameover message when that x wins"
    ;(should= "Game over, x has won\n"
             ;(with-out-str (print-gameover board/x-mark))))

  ;(it "prints a tied game message when nobody wins"
    ;(should= "Game over, tied game\n"
             ;(with-out-str (print-gameover nil))))

  ;(it "prompts user to choose a game type"
    ;(should= "Please choose a game:\n1. 3x3 Tic Tac Toe\n2. 4x4 Tic Tac Toe\n"
             ;(with-out-str (with-in-str "1" (prompt-game-type)))))

  ;(it "returns the value of the selected choice"
    ;(let [choices [{:name "Choice Name"
                    ;:value "Choice Value"}
                   ;{:name "Choice Two Name"
                    ;:value "Choice Two Value"}]]
      ;(with-out-str (with-in-str "1"
                      ;(should= "Choice Value" (prompt-choice "Please choose:" choices))))))

  ;(context "3x3 game type"
    ;(it "prints an empty board"
      ;(let [board (board/empty-board)]
        ;(should= " 0 | 1 | 2 \n---|---|---\n 3 | 4 | 5 \n---|---|---\n 6 | 7 | 8 \n"
                 ;(with-out-str (print-board board)))))

    ;(it "prints a board with some moves"
      ;(let [board [board/x-mark board/nomark board/nomark
                   ;board/nomark board/o-mark board/nomark
                   ;board/nomark board/x-mark board/nomark]]
        ;(should= " x | 1 | 2 \n---|---|---\n 3 | o | 5 \n---|---|---\n 6 | x | 8 \n"
                 ;(with-out-str (print-board board))))))

  ;(context "4x4 game type"
    ;(it "prints an empty board"
      ;(binding [*game-type* game-type/four-by-four]
        ;(let [board (board/empty-board)]
          ;(should= " 0  | 1  | 2  | 3  \n----|----|----|----\n 4  | 5  | 6  | 7  \n----|----|----|----\n 8  | 9  | 10 | 11 \n----|----|----|----\n 12 | 13 | 14 | 15 \n"
                   ;(with-out-str (print-board board)))))))
;)
