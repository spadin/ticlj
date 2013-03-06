(ns ticlj.game.base.printer)

(defprotocol Printer
  (print-line [this message]))

(defrecord BasePrinter [])
(def base-printer (BasePrinter.))

(def printer-defaults
  {:print-line
   (fn [this message]
     (println message))})

(extend BasePrinter
  Printer
  printer-defaults)
