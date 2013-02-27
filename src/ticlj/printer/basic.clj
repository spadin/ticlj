(ns ticlj.printer.basic
  (:use [ticlj.rules.game-type :only (available-game-types *game-type*)]
        [ticlj.player.aplayer :only (available-player-types)])
  (:require [ticlj.board.basic :as board]
            [ticlj.rules.game-type :as game-type]))

(defmulti multi-print-board :game-type)

(defmethod multi-print-board game-type/basic [this]
  (let [separator "---|---|---"
        index-string (fn [board index]
                         (let [mark (board/mark-at-index index board)]
                              (if (= mark board/nomark) index mark)))
        line-string (fn [line board]
                        (str " "
                             (-> board (index-string (* line 3)))
                             " | "
                             (-> board (index-string (+ (* line 3) 1)))
                             " | "
                             (-> board (index-string (+ (* line 3) 2)))
                             " "))]
    (println (line-string 0 (:board this)))
    (println separator)
    (println (line-string 1 (:board this)))
    (println separator)
    (println (line-string 2 (:board this)))))

(defmethod multi-print-board game-type/four-by-four [this]
  (let [separator "----|----|----|----"
        index-string (fn [board index]
                         (let [mark (board/mark-at-index index board)]
                              (format "%-2s" (if (= mark board/nomark) index mark))))
        line-string (fn [line board]
                        (str " "
                             (-> board (index-string (* line 4)))
                             " | "
                             (-> board (index-string (+ (* line 4) 1)))
                             " | "
                             (-> board (index-string (+ (* line 4) 2)))
                             " | "
                             (-> board (index-string (+ (* line 4) 3)))
                             " "))]
    (println (line-string 0 (:board this)))
    (println separator)
    (println (line-string 1 (:board this)))
    (println separator)
    (println (line-string 2 (:board this)))
    (println separator)
    (println (line-string 3 (:board this)))))

(defn print-board [board]
  (multi-print-board {:game-type *game-type* :board board}))

(defn read-integer []
  (try (Integer/parseInt (read-line))
    (catch Exception e
      (println "Invalid input, please try again.")
      (read-integer))))

(defn prompt-integer [message]
  (println message)
  (read-integer))

(defn prompt-choice
  "Presents a message with a list of choices.
   Returns the value of the choice.

   format of choices:
   [{:name  'Option #1 Display Text'
     :value 'option-1-returned-value'}
    {:name  'Option #2 Display Text'
     :value 'option-2-returned-value'}
  "
  [message choices]
  (println message)
  (loop [choices choices
         choice-num 1]
    (let [choice (first choices)
          rest-choices (rest choices)]
      (println (str choice-num ".") (:name choice))
      (if-not (empty? rest-choices) (recur rest-choices (inc choice-num)))))
  (let [selection-num (read-integer)
        idx (dec selection-num)]
    (:value(nth choices idx))))

(defn prompt-player [mark]
  (prompt-integer (str "Player " mark " choose the index of an empty spot for your next move")))

(defn prompt-player-type [player-number]
  (prompt-choice (str "What type of player is player " player-number "?") available-player-types))

(defn print-gameover [winner]
  (if-not (nil? winner)
          (println "Game over," winner "has won")
          (println "Game over, tied game")))

(defn prompt-game-type []
  (prompt-choice "Please choose a game:" available-game-types))
