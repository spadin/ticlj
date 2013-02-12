(ns ticlj.model.player
  (:require [ticlj.io.cli :as printer]
            [ticlj.model.rules :as rules]
            [ticlj.model.board :as board]))

(defprotocol Player
  (move [this board]))
