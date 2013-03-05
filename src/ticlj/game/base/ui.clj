(ns ticlj.game.base.ui
  (:use [ticlj.game.base.printer :only [base-printer print-line]]
        [ticlj.game.base.reader :only [base-reader read-integer read-integer-between]]))

(defprotocol Ui
  (prompt-for-move [this mark])
  (prompt-for-choice [this message choices])
  (prompt-for-game-type [this game-types])
  (prompt-for-player-type [this player-types])
  (display-gameover [this winner]))

(defrecord BaseUi [printer reader])
(def base-ui (BaseUi. base-printer base-reader))

(def ui-defaults
  {:prompt-for-move
   (fn [this mark]
     (print-line (:printer this) (str "Player " (name mark) " please choose an index for your move."))
     (read-integer (:reader this)))

   :prompt-for-choice
   (fn [this message choices]
     (print-line (:printer this) message)
     (doseq [idx (range (count choices))]
       (print-line (:printer this) (str (inc idx) ". " (:name (nth choices idx)))))
     (let [idx (dec (read-integer-between (:reader this) 1 (count choices)))]
       (:value (nth choices idx))))

   :prompt-for-game-type
   (fn [this game-types]
     (prompt-for-choice this "Please choose a game type:" game-types))

   :prompt-for-player-type
   (fn [this player-types]
     (prompt-for-choice this "What type of player is this?" player-types))

   :display-gameover
   (fn [this winner]
     (if (nil? winner)
         (print-line (:printer this) "Game over, tied game.")
         (print-line (:printer this) (str "Game over, " (name winner) " won."))))})

(extend BaseUi
  Ui
  ui-defaults)

