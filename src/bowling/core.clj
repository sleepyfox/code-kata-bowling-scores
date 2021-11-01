(ns bowling.core
  (:gen-class))

(defn score-frame
  "total score for a frame, without bonuses"
  [frame]
  (reduce + frame))

(defn is-strike?
  "is this frame a strike?"
  [frame]
  (= 10 (first frame)))

(defn is-spare?
  "is this frame a spare?"
  [frame]
  (and (= 10 (score-frame frame))
       (not (is-strike? frame))))

(defn score-basic-game
  "total score for ten frames, absent bonuses"
  [game]
  (->> game
       (map score-frame)
       (reduce +)))

;; Idea:
;; (def game       [[1 1] [1 1] ...]
;; (def next-frame (conj (vec (rest game)) [0 0])
;; (def 2nd-frame  (conj (vec (rest next-frame)) [0 0])

;; Then we can have functions that map and combine these three to find
;; the bonus scores without a horribly complex reduce fn...

(defn spare-bonus
  "taking a pair of sequential frames,
  if the first frame is a spare,
  return the first ball of the second frame"
  [[a b]]
  (if (is-spare? a)
    (first b)
    0))

(defn score-spare-bonus
  "total extra score for rolling a spares"
  [game]
  (let [next-frame (conj (vec (rest game)) [0 0]) ;; shiftleft game
        combined (partition 2 (interleave game next-frame))]
    (->> combined
         (map spare-bonus)
         (reduce +))))

(defn strike-bonus
  "taking a pair of sequential frames,
  if the first frame is a strike,
  return the first ball of the second frame"
  [[a b c]]
  (if (is-strike? a)
    (+ (first (flatten [b c]))
       (nth (flatten [b c]) 1))
    0))

(defn score-strike-bonus
  "total extra score for rolling strikes"
  [game]
  (let [next-frame (conj (vec (rest game)) [0 0]) ;; shiftleft game
        next-next-frame (conj (vec (rest next-frame)) [0 0]) ;; shiftleft next-frame
        combined (partition 3 (interleave game next-frame next-next-frame))]
    (->> combined
         (map strike-bonus)
         (reduce +))))

(defn score-game
  "Score the basic game plus spare bonus rolls"
  [game]
  (+ (score-basic-game game)
     (score-spare-bonus game)
     (score-strike-bonus game)))
