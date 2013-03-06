(ns ticlj.player.unbeatable-spec
  (:use [speclj.core]
        [ticlj.player.protocol]
        [ticlj.player.unbeatable]))

(describe "ticlj.player.unbeatable"
  (with sample-game ticlj.game.three-by-three/three-by-three-game)

  (with sample-empty-board-state
    [:# :# :#
     :# :# :#
     :# :# :#])

  (with sample-x-winning-game-board
    [:X :X :X
     :O :O :#
     :# :# :#])

  (with sample-tied-game-board
    [:X :O :X
     :X :X :O
     :O :X :O])

  (with sample-o-winning-game-board
    [:X :X :#
     :O :O :O
     :# :O :#])

  (it "never returns true when max-depth is nil"
    (should= false
             (max-depth-reached? 1 nil)))

  (it "can identify max-depth has been reached"
    (should= true
             (max-depth-reached? 2 1)))

  (it "can identify max-depth hasn't been reached"
    (should= false
             (max-depth-reached? 1 2)))

  (it "returns a score of 1000 for winning at depth 0"
    (should= 1000
             (calculate-score @sample-game :X @sample-x-winning-game-board 0 0)))

  (it "returns a score of 0 for tied game"
    (should= 0
             (calculate-score @sample-game :X @sample-tied-game-board 0 0)))

  (it "returns a score of -1000 for losing game at 0 depth"
    (should= -1000
             (calculate-score @sample-game :X @sample-o-winning-game-board 0 0)))

  (it "returns the ab-value when game is not over"
    (let [empty-board-state [:# :# :#
                             :# :# :#
                             :# :# :#]]
      (should= -9999
               (calculate-score @sample-game :X empty-board-state 0 -9999))))

  (it "returns the move the fills the board"
    (let [board-state [:X :X :O
                       :O :O :X
                       :X :# :O]]
      (try
        (alpha-beta @sample-game board-state nil)
        (catch Exception e (println e)))
      (should= 7
               (alpha-beta @sample-game board-state nil))))

  (it "returns a move to win game"
    (let [board-state [:X :O :X
                       :# :O :#
                       :O :# :X]]
      (should= 5
               (alpha-beta @sample-game board-state nil))))

  (context "slow running tests" (tags :slow-tests)
    (it "returns the best first move"
      (should= 0
              (alpha-beta @sample-game @sample-empty-board-state nil)))

    (it "returns the best second move"
      (let [board-state [:X :# :#
                         :# :# :#
                         :# :# :#]]
        (should= 4
                 (alpha-beta @sample-game board-state nil))))))
