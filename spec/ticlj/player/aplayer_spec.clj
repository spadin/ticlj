(ns ticlj.player.aplayer-spec
  (:use [speclj.core] [ticlj.spec-helper] [ticlj.player.aplayer]))

(describe "ticlj.player.aplayer"
  (it "defines the Player protocol"
    (should= true
             (boolean (resolve `APlayer)))))
