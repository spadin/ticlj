(ns ticlj.player-spec
  (:use [speclj.core]
        [ticlj.player]))

(describe "ticlj.player"
  (it "returns a human-player"
    (should= ticlj.player.human.Human
             (type (get-player-of-type "ticlj.player.human/human-player"))))

  (it "returns an unbeatable-ai-player"
    (should= ticlj.player.unbeatable.UnbeatableAI
             (type (get-player-of-type "ticlj.player.unbeatable/unbeatable-ai-player"))))

  (it "returns a medium-ai-player"
    (should= ticlj.player.medium.MediumAI
             (type (get-player-of-type "ticlj.player.medium/medium-ai-player"))))

  (it "returns a easy-ai-player"
    (should= ticlj.player.easy.EasyAI
             (type (get-player-of-type "ticlj.player.easy/easy-ai-player")))))
