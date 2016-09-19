(ns hacker-rank.core
  (:gen-class))

; https://www.hackerrank.com/challenges/equal-stacks  
(use '[clojure.string :only (split triml)])
(defprotocol IStack
  (remove [this]))

(defrecord Stack [height cylinders]
  IStack
  (remove [this]
    (Stack. (- height (or (first cylinders) 0)) (rest cylinders))))

(defn new-stack [cylinders]
  (->Stack (apply + cylinders) cylinders))

(comment
  (let [n1_temp (read-line)
        n1_t    (split n1_temp #"\s+")
        n1      (Integer/parseInt (n1_t 0))
        n2      (Integer/parseInt (n1_t 1))
        n3      (Integer/parseInt (n1_t 2))
        h1_temp (read-line)
        h1_t    (split h1_temp #"\s+")
        h1      (map #(Integer/parseInt %) h1_t)
        h2_temp (read-line)
        h2_t    (split h2_temp #"\s+")
        h2      (map #(Integer/parseInt %) h2_t)
        h3_temp (read-line)
        h3_t    (split h3_temp #"\s+")
        h3      (map #(Integer/parseInt %) h3_t)]

    (let [stacks [
                  h1 h2 h3
                  ;                [3 2 1 1 1]
                  ;               [4 3 2]
                  ;              [1 1 4 1]
                  ]]
      (loop [stacks (map new-stack stacks)]
        (let [
              ;_              (println heights)
              same-height?   (apply = (map :height stacks))
              ;_              (println "equal heights " same-height?)
              ordered-stacks (sort-by :height > stacks)
              [tallest & rest-stacks] ordered-stacks
              ;_              (println "tallest stack is " tallest)
              ;_              (println "other stacks are " rest-stacks)
              new-stacks     (conj rest-stacks (remove tallest))
              ;_              (println "new stacks are " new-stacks)
              ]
          (if same-height?
            (println (:height (first stacks)))
            (recur new-stacks)))))
    ))

(defn -main [& args])