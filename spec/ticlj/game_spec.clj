(ns ticlj.game-spec
  (:use [speclj.core]
        [ticlj.spec-helper]
        [ticlj.game])
  (:require [ticlj.model.board :as board]
            [ticlj.model.player :as player]
            [ticlj.model.player.human :as human]
            [ticlj.model.player.easy :as easy]
            [ticlj.model.player.medium :as medium]
            [ticlj.model.player.unbeatable :as unbeatable])
  (:import [ticlj.model.player.unbeatable UnbeatableAI]
           [ticlj.model.player.easy EasyAI]
           [ticlj.model.player.medium MediumAI]
           [ticlj.model.player.human Human]))

(describe "ticlj.game"
  (it "prints that the game is a tie"
    (should (.contains (with-out-str (with-in-str (make-input '(0 1 2 5 3 6 4 8 7))
                         (play 1 1)))
                       "tied game")))

  (it "prints that x has won the game"
    (should (.contains (with-out-str (with-in-str (make-input '(0 3 1 4 2))
                         (play 1 1)))
                       "x has won")))

  (it "prints that o has won the game"
    (should (.contains (with-out-str (with-in-str (make-input '(0 3 1 4 7 5))
                         (play 1 1)))
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

  (it "calls play with players when game is started"
    (with-out-str (with-in-str (make-input '(1 1))
      (with-redefs [play (fn [player-1 player-2] "play-called")]
                   (should= "play-called" (start-game)))))))
