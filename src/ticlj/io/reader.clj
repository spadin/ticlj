(ns ticlj.io.reader
  (:use [ticlj.io.printer]))

(defn read-integer []
  (try (Integer/parseInt (read-line))
    (catch Exception e
      (print-line "Invalid input, please try again.")
      (read-integer))))

(defn read-integer-between [low high]
  (let [choice (read-integer)]
    (if (and (<= choice high) (>= choice low))
        choice
        (do
          (print-line "Invalid input, please try again.")
          (recur low high)))))

