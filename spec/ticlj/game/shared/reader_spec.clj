(ns ticlj.game.shared.reader-spec
  (:use [speclj.core]
        [ticlj.spec-helper]
        [ticlj.game.shared.reader]))

(describe "ticlj.game.shared.reader"
  (context "#read-integer"
    (it "reads an integer from input"
      (with-out-str (with-in-str "1"
        (should= 1
                (read-integer)))))

    (it "reads another integer from input"
      (with-out-str (with-in-str "2"
        (should= 2
                (read-integer)))))

    (it "keeps prompting for input until an integer is received"
      (with-out-str (with-in-str (make-input `("Hello" "#" "2"))
        (should= 2
                (read-integer))))))

  (context "#read-integer-between"
    (it "reads an integer from input"
      (with-out-str (with-in-str "1"
        (should= 1
                (read-integer-between 1 2)))))))
