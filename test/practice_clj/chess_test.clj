(ns practice-clj.chess-test
  (:require [clojure.test :refer [are deftest is testing]]
            [practice-clj.chess :refer [knight-attack?]])
  (:import (clojure.lang ExceptionInfo)))

(deftest test-knight-attack
  (testing "able to attack"
    (are [white black] (knight-attack? white black)
      "a8" "b6"
      "b7" "d8"
      "c6" "b8"
      "d1" "f2"
      "e3" "g2"
      "f8" "h7"
      "g1" "h3"
      "h4" "g2"))
  (testing "unable to attack"
    (are [white black] (not (knight-attack? white black))
      "f1" "g2"
      "d4" "f4"))

  (testing "error handling"
    (is (thrown-with-msg? ExceptionInfo #"Black and white cannot start in the same position." (knight-attack? "b4" "b4")))
    (is (thrown-with-msg? ExceptionInfo #"Invalid input: `b9`" (knight-attack? "a8" "b9")))
    (is (thrown-with-msg? ExceptionInfo #"Invalid input: `a0`" (knight-attack? "a0" "b1")))
    (is (thrown-with-msg? ExceptionInfo #"Invalid input: `i5`" (knight-attack? "g3" "i5")))
    (is (thrown-with-msg? ExceptionInfo #"Invalid input: `not`" (knight-attack? "not" "valid")))
    (is (thrown-with-msg? ExceptionInfo #"Invalid input: ``" (knight-attack? "" "")))))
