(ns ticlj.io.printer-spec
  (:use [speclj.core]
        [ticlj.io.printer]))

(describe "ticlj.io.printer"
  (context "/print-line"
    (it "prints a message"
      (should= "This is a message.\n"
               (with-out-str (print-line "This is a message."))))))

