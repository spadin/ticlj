(ns ticlj.game.base.printer-spec
  (:use [speclj.core]
        [ticlj.game.base.printer]))

(describe "ticlj.game.base.printer"
  (context "#print-line"
    (it "prints a message"
      (should= "This is a message.\n"
               (with-out-str (print-line base-printer "This is a message."))))))
