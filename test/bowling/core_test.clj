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
      (is (= 50 (score-game open-game))))))


(deftest checking-for-spare-frame
  (testing "A frame where the player leaves pins standing is not a spare"
    (is (not (is-spare? [1 2]))))

  (testing "A frame where the player knocks down all pins in two balls is a spare"
    (let [spare-frame [2 8]]
      (is (not (is-spare? spare-frame)))))

  (testing "A frame where the player strikes is not a spare"
    (let [strike-frame [10]]
      (is (not (is-spare? strike-frame))))))
