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
  (context "playing the game"
    (around [it]
      (binding [*players* [(Human.) (Human.)]]
        (it)))

    (it "prints that the game is a tie"
      (should (.contains (with-out-str (with-in-str (make-input '(0 1 2 5 3 6 4 8 7))
                           (play)))
                         "tied game")))

    (it "prints that x has won the game"
      (should (.contains (with-out-str (with-in-str (make-input '(0 3 1 4 2))
                           (play)))
                         "x has won")))

    (it "prints that o has won the game"
      (should (.contains (with-out-str (with-in-str (make-input '(0 3 1 4 7 5))
                           (play)))
                         "o has won"))))

  (it "builds a Human player"
    (should= true
             (instance? Human (construct-player "Human"))))

  (it "builds an UnbeatableAI player"
    (should= true
             (instance? UnbeatableAI (construct-player "UnbeatableAI"))))

  (it "builds a MediumAI player"
    (should= true
             (instance? MediumAI (construct-player "MediumAI"))))

  (it "builds a EasyAI player"
    (should= true
             (instance? EasyAI (construct-player "EasyAI"))))

  (it "calls play with players and game type when game is started"
    (with-out-str (with-in-str (make-input '(1 1 1))
      (with-redefs [play (fn [] "play-called")]
                   (should= "play-called" (start-game))))))

  (it "returns the second player when first player is the current player"
    (let [current-player (first *players*)]
      (should= (second *players*)
               (toggle-player current-player))))

  (it "returns the first player when second player is the current player"
    (let [current-player (second *players*)]
      (should= (first *players*)
               (toggle-player current-player)))))
