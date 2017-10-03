(ns practice-clj.buildword-test
  (:require [clojure.test :refer [are deftest]]
            [practice-clj.buildword :refer [build-word]]))

(deftest test-build-word
  (are [expected word fragments] (= expected (build-word word fragments))
    4 "buildword" ["buil" "dwor" "bu" "ild" "wo" "rd"]
    2 "answer" ["wer" "ans"]
    1 "aaaaaa" ["a" "aa" "aaa" "aaaa" "aaaaa" "aaaaaa"]
    18 "veeerrryy ttrrricckkkyy tteessssttt" ["tt" "t" "ee" "e" "v" "rr" "rrr"
                                              "kkk" "cc" "ssss" "y" "yy" " "
                                              "ii" "i"]
    2 "golang" ["g" "lang" "golan"]
    0 "golang" ["g" "lang" "gola"]))
