(ns tiny-maze.solver)

(def adjs [[0 -1] [0 1] [-1 0] [1 0]])

(defn start-pos [maze]
  (->> maze
       (map-indexed (fn [x row] [x (.indexOf row :S)]))
       (filter #(<= 0 (second %)))
       (first)))

(defn solve [maze [x y :as pos]]
  (when-let [v (get-in maze pos)]
    (let [nmaze (assoc-in maze pos :x)]
      (cond
        (#{:E} v) nmaze
        (#{:S 0} v) (some identity
                          (map #(solve nmaze (map + pos %)) adjs))))))

(defn solve-maze [maze]
  (->> maze
       (start-pos)
       (solve maze)))
