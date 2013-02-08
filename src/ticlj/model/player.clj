(ns ticlj.model.player
  (:require [ticlj.io.cli :as printer]
            [ticlj.model.board :as board]))

(defprotocol Player
  (move [this board])
  (mark [this]))

(defrecord Human [mark]
  Player
  (move [this board]
    (try (board/set-mark-at-index (:mark this) (printer/prompt-player (:mark this)) board)
      (catch Exception e
        (println (str "Invalid move, please try again." e))
        (move this board))))
  (mark [this] (:mark this)))
