(ns ticlj.player.medium
  (:use [ticlj.player.unbeatable]
        [ticlj.rules.game-type :only (*game-type*)])
  (:require [ticlj.player.aplayer :as player]
            [ticlj.board.basic :as board]
            [ticlj.rules.game-type :as game-type])
  (:import [ticlj.player.aplayer APlayer]))

(defn determine-max-depth []
  (if (= *game-type* game-type/basic) 4 2))

(defrecord MediumAI []
  APlayer
  (move [this board]
    (alpha-beta (board/current-mark board) board 4)))
