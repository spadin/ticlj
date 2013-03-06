;(ns ticlj.core
  ;(:use [ticlj.rules.game-type :only (*game-type*)])
  ;(:require [ticlj.board.basic :as board]
            ;[ticlj.printer.basic :as printer]
            ;[ticlj.rules.basic :as rules]
            ;[ticlj.player.aplayer :as player]
            ;[ticlj.player.human :as human]
            ;[ticlj.player.easy :as easy]
            ;[ticlj.player.medium :as medium]
            ;[ticlj.player.unbeatable :as unbeatable])
  ;(:import [ticlj.player.human Human]
           ;[ticlj.player.easy EasyAI]
           ;[ticlj.player.medium MediumAI]
           ;[ticlj.player.unbeatable UnbeatableAI]))

;(def ^:dynamic *players* [])

;(defn construct-player [s]
  ;(clojure.lang.Reflector/invokeConstructor (resolve (symbol s)) (to-array [])))

;(defn toggle-player [current-player]
  ;(if (= current-player (first *players*))
    ;(second *players*)
    ;(first *players*)))

;(defn play
  ;([] (play (board/empty-board) (first *players*)))
  ;([board player]
    ;(printer/print-board board)
    ;(if (rules/gameover? board)
      ;(printer/print-gameover (rules/winner board))
      ;(recur (-> board (board/set-mark-at-index
                         ;(board/current-mark board)
                         ;(player/move player board)))
             ;(toggle-player player)))))

;(defn start-game []
  ;(binding [*game-type* (printer/prompt-game-type)
            ;*players* [(construct-player (printer/prompt-player-type 1))
                       ;(construct-player (printer/prompt-player-type 2))]]
    ;(play)))

;(defn -main [& args]
  ;(start-game))
