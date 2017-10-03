(ns practice-clj.buildword
  (:require [clojure.string :as str]))

(defn get-info [word]
  (fn [fragment]
    (loop [index 0
           output []]
      (if (<= (count word) index)
        output
        (let [start (str/index-of word fragment index)]
          (if (nil? start)
            output
            (recur (inc start) (conj output {:start start :end (+ start (count fragment)) :fragment fragment}))))))))

(defn build [depth next-index fragments length]
  (let [matched (filter #(= next-index (:start %)) fragments)
        enders (filter #(= length (:end %)) matched)]
    (cond (seq enders)
          (inc depth)

          (seq matched)
          (let [deeper (keep #(build (inc depth) (:end %) fragments length) matched)]
            (when (seq deeper)
              (apply min deeper))))))

(defn build-word [word fragments]
  (let [fragmap (reverse (sort-by :end (mapcat (get-info word) fragments)))
        shortest (build 0 0 fragmap (count word))]
    (if (nil? shortest) 0 shortest)))
