(ns bowling.core
  (:gen-class))

(defn score-frame
  "total score for a frame, without bonuses"
  [frame]
  (reduce + frame))
