(ns alphabet-cipher.coder)

(def alphabet-base (int \a))
(def alphabet-length (+ 1 (- (int \z) (int \a))))

(defn char->int [c]
  (- (int c) alphabet-base))

(defn int->char [i]
  (char (+ alphabet-base (mod i alphabet-length))))

(defn extended-keyword [keyword length]
  (if (< (count keyword) length)
    (let [extended-keyword (range length)
          initial-length (count keyword)]
      (mapv #(nth keyword (mod % initial-length)) extended-keyword))
    (seq keyword)))

(defn rotate-letter [op c1 c2]
  (int->char (op (char->int c1) (char->int c2))))

(defn rotate-word [keyword message op]
  (->> (count message)
       (extended-keyword keyword)
       (map #(rotate-letter op %1 %2) (seq message))
       (apply str)))

(defn encode [keyword message]
  (rotate-word keyword message +))

(defn decode [keyword message]
  (rotate-word keyword message -))

(defn decipher [cipher message]
  (decode message cipher)
  ; TODO: find the size of the pattern
  )

