(ns practice-clj.jaro-test
  (:require [clojure.test :refer [are deftest]]
            [practice-clj.jaro :refer [jaro-distance]]))

(deftest test-jaro
  (are [a b expected] (= expected (jaro-distance a b))
    "dixon" "dicksonx" (/ 23 30)
    "dicksonx" "DIXON" (/ 23 30)
    "Cosmo" "Kosmo" (/ 13 15)
    "Cosmer Kramo" "Kosmoo Karme" (/ 25 36)
    "MARTHA" "marhta" (/ 17 18)
    "five" "ten" 0
    "jones" "johnson" (/ 83 105)
    "impression" "impression" 1
    "jaro" "oraj" (/ 1 2)
    "" "" 1
    " " " " 1
    "c++" "++c" (/ 5 9)
    "a" "z" 0))
