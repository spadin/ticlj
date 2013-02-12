(ns ticlj.model.player-spec
  (:use [speclj.core] [ticlj.spec-helper] [ticlj.model.player]))

(describe "ticlj.model.player"
  (it "defines the Player protocol"
    (should= true
             (boolean (resolve `Player)))))
