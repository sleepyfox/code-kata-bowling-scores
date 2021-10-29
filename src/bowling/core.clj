(ns bowling.core
  (:gen-class))

(defn score-frame
  "total score for a frame, without bonuses"
  [frame]
  (reduce + frame))

(defn is-spare?
  "is this frame a spare?"
  [frame]
  (and (= 10 score-frame)
       (not (= 10 (first frame)))))

(defn score-basic-game
  "total score for ten frames, absent bonuses"
  [game]
  (->> game
       (map score-frame)
       (reduce +)))

(defn score-spare-bonus
  "extra score for rolling a spare in a frame is the next roll"
  [game]
  0)

(defn score-game
  "Score the basic game plus spare bonus rolls"
  [game]
  (+ (score-basic-game game)
     (score-spare-bonus game)))
