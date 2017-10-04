(ns practice-clj.coins-test
  (:require [clojure.test :refer [are deftest]]
            [practice-clj.coins :refer [piles]]))

(deftest test-piles
  (are [coins expected] (= expected (piles coins))
    1 1
    2 2
    3 3
    4 5
    5 7
    6 11
    7 15
    10 42
    15 176
    22 1002
    30 5604
    100 190569292
    444 94720370257893471820N
    912 684408913209287275550344075013N))
