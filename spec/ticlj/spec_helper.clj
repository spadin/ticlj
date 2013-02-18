(ns ticlj.spec-helper
  (:require [ticlj.model.board :as board]))

(defmacro dbg[x] `(let [x# ~x] (println "dbg:" '~x "=" x#) x#))

; helps with passing multiple inputs when using with-in-str
; http://mikeebert.tumblr.com/post/32243344470/mocking-input-in-clojure-thanks-colin
(defn make-input [coll]
  (apply str (interleave coll (repeat "\n"))))

(def tied-game-board
  [board/x-mark board/o-mark board/x-mark
   board/x-mark board/x-mark board/o-mark
   board/o-mark board/x-mark board/o-mark])

(def x-winning-game-board
  [board/x-mark board/x-mark board/x-mark
   board/o-mark board/o-mark board/nomark
   board/nomark board/nomark board/nomark])

(def o-winning-game-board
  [board/x-mark board/x-mark board/nomark
   board/o-mark board/o-mark board/o-mark
   board/nomark board/x-mark board/nomark])
