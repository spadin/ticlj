(ns ticlj.spec-helper)

(defmacro dbg[x] `(let [x# ~x] (println "dbg:" '~x "=" x#) x#))

; helps with passing multiple inputs when using with-in-str
; http://mikeebert.tumblr.com/post/32243344470/mocking-input-in-clojure-thanks-colin
(defn make-input [coll]
  (apply str (interleave coll (repeat "\n"))))
