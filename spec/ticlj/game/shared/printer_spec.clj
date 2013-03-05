(ns ticlj.game.shared.printer-spec
  (:use [speclj.core]
        [ticlj.game.shared.printer]))

(describe "ticlj.game.shared.printer"
  (context "/print-line"
    (it "prints a message"
      (should= "This is a message.\n"
               (with-out-str (print-line "This is a message."))))))

