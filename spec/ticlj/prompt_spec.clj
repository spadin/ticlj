(ns ticlj.prompt-spec
  (:use [speclj.core]
        [ticlj.spec-helper]
        [ticlj.prompt]))

(describe "ticlj.prompt"
  (context "/prompt-for-move"
    (it "should ask for a move"
      (should= "Player X please choose an index for your move.\n\n"
               (with-out-str (with-in-str "0" (prompt-for-move :X))))))

  (context "/prompt-for-choice"
    (with choices [{:name "Choice 1"
                   :value "choice-1"}
                  {:name "Choice 2"
                   :value "choice-2"}])
    (it "asks to make a choice"
      (should= "Please make a choice\n1. Choice 1\n2. Choice 2\n\n"
               (with-out-str (with-in-str "1" (prompt-for-choice "Please make a choice" @choices)))))

    (it "returns the value of the choice"
      (with-out-str (with-in-str "2"
        (should= "choice-2"
                 (prompt-for-choice "Please make a choice" @choices)))))

    (it "continues asking until a valid choice is made"
      (with-out-str (with-in-str (make-input `("0" "10" "1"))
        (should= "choice-1"
                 (prompt-for-choice "Please make a choice" @choices))))))

  (context "#prompt-for-game-type"
    (with game-types [{:name "3x3 Tic Tac Toe"
                       :value "three-by-three"}
                      {:name "4x4 Tic Tac Toe"
                       :value "four-by-four"}])
    (it "asks user to choose a game type"
      (should= "Please choose a game type:\n1. 3x3 Tic Tac Toe\n2. 4x4 Tic Tac Toe\n\n"
               (with-out-str (with-in-str "1" (prompt-for-game-type @game-types))))))

  (context "#prompt-for-player-type"
    (with player-types [{:name "Human"
                       :value "Human"}
                      {:name "Unbeatable AI"
                       :value "UnbeatableAI"}])
    (it "asks user to choose a player type"
      (should= "What type of player is this?\n1. Human\n2. Unbeatable AI\n\n"
               (with-out-str (with-in-str "1" (prompt-for-player-type @player-types)))))))
