(ns ticlj.game.shared.logic-spec
  (:use [speclj.core]
        [ticlj.game.shared.winning-combination-maker]
        [ticlj.game.shared.logic]))

(describe "ticlj.game.shared.logic"
  (with sample-winning-combinations
        (vec (flatten (merge (horizontal-wins 3 2)
                             (vertical-wins 3 2)
                             #{0 4 8} #{2 4 6}))))

  (context "/winning-combination-met?"
    (it "determines a winning combination is not in the board"
      (let [board-state [:X :# :#
                         :# :# :#
                         :# :# :#]
            winning-combination #{0 1 2}]
        (should= false
                 (winning-combination-met? board-state winning-combination))))

    (it "determines :X has met a winning combination"
      (let [board-state [:X :X :X
                         :# :# :#
                         :# :# :#]
            winning-combination #{0 1 2}]
        (should= :X
                 (winning-combination-met? board-state winning-combination)))))

  (context "/winner?"
    (it "determines there is no winner when the board is not full"
      (let [board-state [:X :# :#
                         :# :# :#
                         :# :# :#]]
        (should= false
                 (winner? board-state @sample-winning-combinations))))

    (it "determines there is a winner"
      (let [board-state [:# :# :#
                         :X :X :X
                         :# :# :#]]
        (should= true
                 (winner? board-state @sample-winning-combinations)))))

  (context "/winner"
    (it "determines there is no winner when the board is not full"
      (let [board-state [:X :# :#
                         :# :# :#
                         :# :# :#]]
        (should= nil
                 (winner board-state @sample-winning-combinations))))

    (it "determines :X is the winner"
      (let [board-state [:# :# :#
                         :X :X :X
                         :# :# :#]]
        (should= :X
                 (winner board-state @sample-winning-combinations)))))

  (context "/gameover"
    (it "determines the game is not over"
      (let [board-state [:# :O :#
                         :X :X :#
                         :# :# :#]]
        (should= false
                 (gameover? board-state @sample-winning-combinations))))

    (it "determines the game is over when there is a winner"
      (let [board-state [:O :# :X
                         :O :X :#
                         :X :# :#]]
        (should= true
                 (gameover? board-state @sample-winning-combinations))))

    (it "determines the game is over when the board is full"
      (let [board-state [:X :O :X
                         :X :O :O
                         :O :X :X]]
        (should= true
                 (gameover? board-state @sample-winning-combinations))))))

