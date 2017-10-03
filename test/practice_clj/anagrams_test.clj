(ns practice-clj.anagrams-test
  (:require [clojure.test :refer [are deftest]]
            [practice-clj.anagrams :refer [find-anagrams]]))

(deftest find-anagrams-test
  (let [dict "resources/anagrams_dictionary.txt"]
    (are [word expected] (= expected (find-anagrams dict word))

      "" []
      " " []
      "Eleven plus two" ["Twelve plus one"]
      "Clint Eastwood" ["Old West Action"]
      "Protectionism" ["Cite no imports", "Nice to imports"]
      "Protection" []
      "Funeral" ["Real fun"]
      "The Doors" []
      "funeral" ["Real fun"])))
