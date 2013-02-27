(ns ticlj.rules.game-type)

(def ^:constant basic :basic)
(def ^:constant four-by-four :four-by-four)
(def ^:constant three-cubed :three-cubed)

(def ^:constant available-game-types [{:name "3x3 Tic Tac Toe"
                                       :value basic}
                                      {:name "4x4 Tic Tac Toe"
                                       :value four-by-four}])

(def ^:dynamic *game-type* basic)

