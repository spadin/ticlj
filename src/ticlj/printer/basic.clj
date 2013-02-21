(ns ticlj.printer.basic
  (:use [ticlj.rules.game-type :only (*game-type*)])
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

(defn print-board [board]
  (multi-print-board {:game-type *game-type* :board board}))

(defn prompt-integer [message]
  (println message)
  (try (Integer/parseInt (read-line))
    (catch Exception e
      (println "Invalid input, please try again.")
      (prompt-integer message))))

(defn prompt-player [mark]
  (prompt-integer (str "Player " mark " choose the index of an empty spot for your next move")))

(defn prompt-player-type [player-number]
  (prompt-integer (str "What type of player is player " player-number "?\n1. Human\n2. Unbeatable AI\n3. Easy AI\n4. Medium AI")))

(defn print-gameover [winner]
  (if-not (nil? winner)
          (println "Game over," winner "has won")
          (println "Game over, tied game")))

(defn prompt-game-type []
  (prompt-integer (str "Please choose a game:\n1. 3x3 Tic Tac Toe\n2. 4x4 Tic Tac Toe")))
