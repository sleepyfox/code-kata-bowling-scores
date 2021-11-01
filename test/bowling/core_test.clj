(ns bowling.core-test
  (:require [clojure.test :refer :all]
            [bowling.core :refer :all]))

(deftest scoring-a-frame
  (testing "Two gutter rolls score zero"
    (let [gutter-frame [0 0]]
      (is (= 0 (score-frame gutter-frame)))))

  (testing "A spare scores ten"
    (let [spare-frame [2 8]]
      (is (= 10 (score-frame spare-frame))))))

(deftest scoring-a-game
  (testing "A game of all gutter rolls scores zero"
    (let [gutter-frame [0 0]
          gutter-game  (repeat 10 gutter-frame)]
      (is (= 0 (score-game gutter-game)))))

  (testing "A game of all opens (5) scores fifty"
    (let [open-frame [2 3]
          open-game  (repeat 10 open-frame)]
      (is (= 50 (score-game open-game)))))

  (testing "A game with a spare scores 57"
    (let [open-frame [2 3]
          spare-frame [2 8]
          spare-game (concat [spare-frame] (repeat 9 open-frame))]
      (is (= (+ 45 10 2) (score-game spare-game)))))

  (testing "A game with a strike scores 60"
    (let [open-frame [2 3]
          strike-frame [10]
          strike-game (concat [strike-frame] (repeat 9 open-frame))]
      (is (= (+ 45 10 5) (score-game strike-game))))))

(deftest checking-for-spare-frame
  (testing "A frame where the player leaves pins standing is not a spare"
    (is (not (is-spare? [1 2]))))

  (testing "A frame where the player knocks down all pins in two balls is a spare"
    (let [spare-frame [2 8]]
      (is (is-spare? spare-frame))))

  (testing "A frame where the player strikes is not a spare"
    (let [strike-frame [10]]
      (is (not (is-spare? strike-frame))))))

(deftest check-spare-bonus-score
  (testing "A two frame match, where the first is a spare, should get a bonus"
    (let [game [[4 6] [1 0]]]
      (is (= 1 (score-spare-bonus game)))))

  (testing "A game with two spares should get two bonuses"
    (let [game [[4 6] [5 5] [1 1]]]
      (is (= (+ 5 1) (score-spare-bonus game))))))

(deftest checking-for-strike
  (testing "A frame in which pins are left standing is not a strike"
    (is (not (is-strike? [1 0]))))

  (testing "A frame when ten pins are down, but not all at once is not a strike"
    (is (not (is-strike? [5 5]))))

  (testing "A strike is when all the pins are down on the first ball"
    (is (is-strike? [10]))))

(deftest check-strike-bonus
  (testing "A frame that is not a strike gets no bonus"
    (is (= 0 (strike-bonus [[5 5] [2 8] [1 3]]))))
  (testing "A frame with a strike gets the next two normal rolls as bonus"
    (is (= 6 (strike-bonus [[10] [2 4] [5 5]]))))
  (testing "A frame with two strikes gets the strike and the next normal roll"
    (is (= 15 (strike-bonus [[10] [10] [5 2]])))))
