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

(defn prompt-integer [message]
  (println message)
  (try (Integer/parseInt (read-line))
    (catch Exception e
      (println "Invalid input, please try again.")
      (prompt-integer message))))

(defn prompt-integer-choice [message choices]
  (println message)
  (loop [choices choices
         number 1]
    (let [choice (first choices)
          rest-choices (rest choices)]
      (println (str number ".") (:name choice))
      (if-not (empty? rest-choices) (recur rest-choices (inc number)))))
  (prompt-integer ""))

(defn prompt-player [mark]
  (prompt-integer (str "Player " mark " choose the index of an empty spot for your next move")))

(defn prompt-player-type [player-number]
  (prompt-integer-choice (str "What type of player is player " player-number "?") available-player-types))

(defn print-gameover [winner]
  (if-not (nil? winner)
          (println "Game over," winner "has won")
          (println "Game over, tied game")))

(defn prompt-game-type []
  (let [selection (prompt-integer-choice "Please choose a game:" available-game-types)]
    (if (= 1 selection)
        game-type/basic
        game-type/four-by-four)))

