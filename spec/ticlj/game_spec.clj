(ns ticlj.game-spec
  (:use [speclj.core] [ticlj.game])
  (:require [ticlj.model.board :as board]))

(describe "ticlj.game"
  (before (board/reset-board))

  (it "determines the next player"
    (should= board/o-mark
             (next-player board/x-mark))
    (should= board/x-mark
             (next-player board/o-mark))))
