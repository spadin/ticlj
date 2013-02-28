(ns ticlj.game.four-by-four.board
  (:use [ticlj.game.base.board :only [board-defaults]]))

(defrecord FourByFourBoard [])
(def four-by-four-board (FourByFourBoard.))

(extend FourByFourBoard
  ticlj.game.base.board/Board
  (merge board-defaults
         {:size
          (fn [this]
            16)}))
