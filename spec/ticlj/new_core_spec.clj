(ns ticlj.new-core-spec
  (:use [speclj.core]
        [ticlj.new-core]))

(defn make-input [coll]
  (apply str (interleave coll (repeat "\n"))))

(describe "ticlj.new-core"
  (it "calls play"
    (with-redefs [play (fn [game players board-state] :stubbed)]
      (with-out-str
        (should= :stubbed
                 (with-in-str (make-input '(1 1 1)) (start-game))))))

  (it "calls play and starts"
    (with-out-str
      (should= nil
               (with-in-str (make-input '(1 1 1 0 1 2 3 4 5 6)) (start-game))))))
