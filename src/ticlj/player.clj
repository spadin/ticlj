(ns ticlj.player
  (:require [ticlj.player.human]
            [ticlj.player.unbeatable]
            [ticlj.player.medium]
            [ticlj.player.easy]))

(def available-player-types [{:name "Human"
                              :value "ticlj.player.human/human-player"}
                             {:name "Unbeatable AI"
                              :value "ticlj.player.unbeatable/unbeatable-ai-player"}
                             {:name "Medium AI"
                              :value "ticlj.player.medium/medium-ai-player"}
                             {:name "Easy AI"
                              :value "ticlj.player.easy/easy-ai-player"}])

(defn get-player-of-type [player-type]
  @(resolve (symbol player-type)))
