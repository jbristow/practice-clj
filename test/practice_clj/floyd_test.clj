(ns practice-clj.floyd-test
  (:require [clojure.test :refer [are deftest]]
            [practice-clj.floyd :refer [triangle]]))

(deftest floyd-triangle-test
  (are [input expected] (= expected (triangle input))
    0 []
    1 [[1]]
    2 [[1] [2 3]]
    3 [[1] [2 3] [4 5 6]]
    4 [[1] [2 3] [4 5 6] [7 8 9 10]]
    5 [[1] [2 3] [4 5 6] [7 8 9 10] [11 12 13 14 15]]
    6 [[1] [2 3] [4 5 6] [7 8 9 10] [11 12 13 14 15] [16 17 18 19 20 21]]))

