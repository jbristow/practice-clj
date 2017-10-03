(ns practice-clj.anagrams
  (:require [clojure.string :as str])
  (:import (java.io BufferedReader FileReader)))

(defn clean
  ; Returns a lower-case string with only alpha characters.
  [word]
  (str/lower-case (str/replace word #"[^A-Za-z]|\s" "")))

(defn clean-and-sort
  ; Returns a sorted lower-case sorted list of alpha characters.
  [word]
  (sort (clean word)))

(defn anagram-of?
  ; Returns a function that returns true if input is an anagram of the word.
  ; Useful for filter.
  [word]
  (fn [candidate]
    (and (= (clean-and-sort word) (clean-and-sort candidate))
         (not= (clean word) (clean candidate)))))

(defn find-anagrams
  ; Searches a line separated BufferedReader for anagrams of a given input.
  [dictionary word]
  (let [dict (line-seq (BufferedReader. (FileReader. dictionary)))]
    (filter (anagram-of? word) dict)))
