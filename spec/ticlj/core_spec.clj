(ns ticlj.core-spec
  (:use [speclj.core]
        [ticlj.spec-helper]
        [ticlj.core])
  (:require [ticlj.board.basic :as board]
            [ticlj.player.aplayer :as player]
            [ticlj.player.human :as human]
            [ticlj.player.easy :as easy]
            [ticlj.player.medium :as medium]
            [ticlj.player.unbeatable :as unbeatable])
  (:import [ticlj.player.unbeatable UnbeatableAI]
           [ticlj.player.easy EasyAI]
           [ticlj.player.medium MediumAI]
           [ticlj.player.human Human]))

(describe "ticlj.core"
  (it "prints that the game is a tie"
    (should (.contains (with-out-str (with-in-str (make-input '(0 1 2 5 3 6 4 8 7))
                         (play 1 1 1)))
                       "tied game")))

  (it "prints that x has won the game"
    (should (.contains (with-out-str (with-in-str (make-input '(0 3 1 4 2))
                         (play 1 1 1)))
                       "x has won")))

  (it "prints that o has won the game"
    (should (.contains (with-out-str (with-in-str (make-input '(0 3 1 4 7 5))
                         (play 1 1 1)))
                       "o has won")))

  (it "builds a human player"
    (should= true
             (instance? Human (build-player 1 1 board/x-mark))))

  (it "builds an UnbeatableAI player"
    (should= true
             (instance? UnbeatableAI (build-player 2 1 board/x-mark))))

  (it "builds an EasyAI player"
    (should= true
             (instance? EasyAI (build-player 3 1 board/x-mark))))

  (it "builds a MediumAI player"
    (should= true
             (instance? MediumAI (build-player 4 1 board/x-mark))))

  (it "calls play with players and game type when game is started"
    (with-out-str (with-in-str (make-input '(1 1 1))
      (with-redefs [play (fn [game-type player-1 player-2] "play-called")]
                   (should= "play-called" (start-game))))))

  (it "defines the default game-type as 3x3"
    (should= "3x3"
             *game-type*))

  (it "allows re-binding of game-type"
    (binding [*game-type* "4x4"]
      (should= "4x4"
               ticlj.core/*game-type*))))
