(ns ticlj.game.shared.reader
  (:require [ticlj.game.shared.printer :as printer]))

(defn read-integer []
  (try (Integer/parseInt (read-line))
    (catch Exception e
      (printer/print-line "Invalid input, please try again.")
      (read-integer))))

(defn read-integer-between [low high]
  (let [choice (read-integer)]
    (if (and (<= choice high) (>= choice low))
        choice
        (do
          (printer/print-line "Invalid input, please try again.")
          (recur low high)))))
