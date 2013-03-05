(ns ticlj.game.base.reader
  (:use [ticlj.game.base.printer :only [base-printer print-line]]))

(defprotocol Reader
  (read-integer [this])
  (read-integer-between [this low high]))

(defrecord BaseReader [printer])
(def base-reader (BaseReader. base-printer))

(def reader-defaults
  {:read-integer
   (fn [this]
     (try (Integer/parseInt (read-line))
       (catch Exception e
         (print-line (:printer this) "Invalid input, please try again.")
         (read-integer this))))

   :read-integer-between
   (fn [this low high]
     (let [choice (read-integer this)]
       (if (and (<= choice high) (>= choice low))
           choice
           (do
             (print-line base-printer "Invalid input, please try again.")
             (recur this low high)))))})

(extend BaseReader
  Reader
  reader-defaults)
