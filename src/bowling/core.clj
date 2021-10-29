(ns bowling.core
  (:gen-class))

(defn score-frame
  "total score for a frame, without bonuses"
  [frame]
  (reduce + frame))

(defn score-basic-game
  "total score for ten frames, absent bonuses"
  [game]
  (->> game
       (map score-frame)
       (reduce +)))

(defn score-game
  "Score the basic game plus spare bonus rolls"
  [game]
  (score-basic-game game))
