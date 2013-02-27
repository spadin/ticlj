(ns ticlj.player.aplayer)

(def ^:constant available-player-types [{:name "Human"
                                         :value "Human"}
                                        {:name "Unbeatable AI"
                                         :value "UnbeatableAI"}
                                        {:name "Medium AI"
                                         :value "MediumAI"}
                                        {:name "Easy AI"
                                         :value "EasyAI"}])

(defprotocol APlayer
  (move [this board]))
