(ns practice-clj.coins)

(def seed {1 '(1)
           2 '(1 1)})

(defn populate-columns-for
  ; calculates each column based upon previous rows
  [s x]
  (fn [lst i] (conj lst (reduce +' 0 (drop (dec i) (get s (- x i)))))))

(defn populate-row
  ; populates a row of n columns made from grabbing earlier lines
  [s x]
  (assoc s x (conj (reduce (populate-columns-for s x) [] (range 1 x)) 1)))

(defn smallest-size-partition
  ; Generates a chart showing how many possible partitions have a minimum size of i+1
  [n]
  (cond (= n 1) (get seed 1)
        (= n 2) (get seed 2)
        (> n 2)
        (get (reduce populate-row seed (range 3 (inc n))) n)))

(defn piles [n]
  (reduce +' 0 (smallest-size-partition n)))
