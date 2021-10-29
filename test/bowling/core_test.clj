(ns bowling.core-test
  (:require [clojure.test :refer :all]
            [bowling.core :refer :all]))

(deftest scoring-a-frame
  (testing "Two gutter rolls score zero"
    (let [gutter-frame [0 0]]
      (is (= 0 (score-frame gutter-frame)))))

  (testing "a spare scores ten"
    (let [spare-frame [2 8]]
      (is (= 10 (score-frame spare-frame))))))
