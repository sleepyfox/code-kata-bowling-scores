(ns bowling.core
  (:gen-class))

(defn score-frame
  "total score for a frame, without bonuses"
  [frame]
  (reduce + frame))

(defn score-game
  "total score for ten frames, absent bonuses"
  [game]
  (->> game
       (map score-frame)
       (reduce +)))
