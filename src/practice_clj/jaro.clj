(ns practice-clj.jaro
  (:require [clojure.string :as str]))

(defn matching [h i b start end]
  (some #(= h %) (map #(.charAt b %) (range start end))))

(defn start [i d]
  (max 0 (- i d)))

(defn end [i d s2]
  (min (+ i d 1) s2))

(defn index-pairs-by-distance [a-map b-map]
  (let [sa (count a-map)
        sb (count b-map)
        d (int (dec (/ (max sa sb) 2)))]
    (mapcat (fn [i] (map #(vector i (get a-map i)
                                  % (get b-map %))
                         (range (start i d) (end i d sb))))
            (range sa))))

(defn str->char-map [s]
  (zipmap (range (count s)) s))

(defn matching-reducer [{:keys [matched-i matched-k seen] :as m} [i ci k ck]]
  (let [{already-i i} matched-i
        {already-k k} matched-k]
    (if (and (nil? already-i)
             (nil? already-k)
             (= ci ck))
      (assoc m
             :matched-i (assoc matched-i i ci)
             :matched-k (assoc matched-k k ck)
             :seen (inc seen))
      m)))

(defn find-matching [a b]
  (reduce matching-reducer
          {:matched-i {} :matched-k {} :seen 0}
          (index-pairs-by-distance (str->char-map a)
                                   (str->char-map b))))

(defn count-transpositions [{:keys [matched-i matched-k]}]
  (loop [[[i ci] & ri :as all-i] (sort-by key matched-i)
         [[k ck] & rk] (sort-by key matched-k)
         transpos 0]
    (cond (or (nil? i) (nil? k))
          (/ transpos 2)

          (= ci ck)
          (recur ri rk transpos)

          :else
          (recur ri rk (inc transpos)))))

(defn jaro-distance [a b]
  (let [m-map (find-matching (str/lower-case a) (str/lower-case b))
        m (:seen m-map)
        t (count-transpositions m-map)]
    (cond (and (zero? (count a)) (zero? (count b)))
          1
          (zero? m)
          0
          :else
          (/ (+ (/ m (count a)) (/ m (count b)) (/ (- m t) m)) 3))))
