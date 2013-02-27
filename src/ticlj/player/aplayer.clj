(ns ticlj.player.aplayer)

(def ^:constant available-player-types [{:name "Human"
                                         :value "ticlj.player.human.Human"}
                                        {:name "Unbeatable AI"
                                         :value "ticlj.player.unbeatable.UnbeatableAI"}
                                        {:name "Medium AI"
                                         :value "ticlj.player.medium.MediumAI"}
                                        {:name "Easy AI"
                                         :value "ticlj.player.easy.EasyAI"}])

(defprotocol APlayer
  (move [_ board]))
