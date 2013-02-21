(ns ticlj.player-spec
  (:use [speclj.core] [ticlj.spec-helper] [ticlj.player]))

(describe "ticlj.player"
  (it "defines the Player protocol"
    (should= true
             (boolean (resolve `Player)))))
