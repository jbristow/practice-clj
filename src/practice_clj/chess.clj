(ns practice-clj.chess
  (:require [clojure.string :as str]))

(def col-nums {"a" 1 "b" 2 "c" 3 "d" 4 "e" 5 "f" 6 "g" 7 "h" 8})

(defn parse-position [input]
  (let [[_ col row] (re-find #"^\s*([a-hA-H])([1-8])\s*$" (str/lower-case input))]
    (if (or (nil? col) (nil? row))
      (throw (ex-info (format "Invalid input: `%s`" input) {:input input}))

      {:col (get col-nums col) :row (Integer. row)})))

(defn n-away? [a b n]
  (or (= (- n) (- a b))
      (= n (- a b))))

(defn two-away? [a b] (n-away? a b 2))
(defn one-away? [a b] (n-away? a b 1))

(defn knight-attack? [white black]
  (let [{wcol :col wrow :row} (parse-position white)
        {bcol :col brow :row} (parse-position black)]
    (when (= (str/lower-case white) (str/lower-case black))
      (throw (ex-info "Black and white cannot start in the same position." {:input white})))
    (or (and (two-away? wcol bcol) (one-away? wrow brow))
        (and (two-away? wrow brow) (one-away? wcol bcol)))))
