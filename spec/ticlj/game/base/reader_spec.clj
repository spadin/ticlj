(ns ticlj.game.base.reader-spec
  (:use [speclj.core]
        [ticlj.spec-helper]
        [ticlj.game.base.reader]))

(describe "ticlj.game.base.reader"
  (context "#read-integer"
    (it "reads an integer from input"
      (with-out-str (with-in-str "1"
        (should= 1
                (read-integer base-reader)))))

    (it "reads another integer from input"
      (with-out-str (with-in-str "2"
        (should= 2
                (read-integer base-reader)))))

    (it "keeps prompting for input until an integer is received"
      (with-out-str (with-in-str (make-input `("Hello" "#" "2"))
        (should= 2
                (read-integer base-reader))))))

  (context "#read-integer-between"
    (it "reads an integer from input"
      (with-out-str (with-in-str "1"
        (should= 1
                (read-integer-between base-reader 1 2)))))))
