(ns practice-clj.floyd)

(defn sum-to [n]
  (/ (* n (+ n 1)) 2))

(defn triangle-row [n]
  (range (inc (sum-to (dec n)))
         (inc (sum-to n))))

(defn triangle [n]
  (reduce #(conj %1 (triangle-row %2)) []
          (range 1 (inc n))))

