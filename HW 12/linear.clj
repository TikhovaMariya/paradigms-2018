(defn vector_operation [operation]
  (fn [& operators] (apply mapv operation operators)))

(defn matrix_operation [operation]
  (fn [& operators] (apply mapv (vector_operation operation) operators)))

(defn general_operation [operation]
  (fn [& operators]
    (cond
      (number? (first operators)) (apply operation operators)
      (vector? (first operators)) (apply (vector_operation (general_operation operation)) operators))))


(def s+ (general_operation +))
(def s- (general_operation -))
(def s* (general_operation *))

(def v+ (vector_operation +))
(def v- (vector_operation -))
(def v* (vector_operation *))

(defn create_vector [v x] (repeat (count v) x))
(defn v*s [v s] (v* v (create_vector v s)))
(defn scalar [f s] (reduce + (v* f s)))
(defn vect [f s]
  (if (and (compare (count f) 3) (compare (count s) 3))
    (vector (- (* (nth f 1) (nth s 2)) (* (nth f 2) (nth s 1)))
            (- (* (nth f 2) (nth s 0)) (* (nth f 0) (nth s 2)))
            (- (* (nth f 0) (nth s 1)) (* (nth f 1) (nth s 0))))))

(defn create_matrix [m x] (repeat (count m) (create_vector (peek m) x)))
(defn new_matrix [i] (repeat i (vector)))
(defn mul_matrix_vector [res m v]
  (if(> (count m) 0)
    (mul_matrix_vector (conj res (scalar (nth m 0) v)) (subvec m 1) v)
    res))

(def m+ (matrix_operation +))
(def m- (matrix_operation -))
(def m* (matrix_operation *))

(defn m*s [m s] (m* m (create_matrix m s)))
(defn m*v [m v] (mul_matrix_vector (vector) m v))
(defn transpose [m]
  (apply mapv vector m))
(defn m*m_v [res f s]
  (if(> (count s) 0)
    (m*m_v (conj res (m*v f (peek s))) f (pop s))
    res))
(defn m*m [f s] (transpose (m+ (rseq (m*m_v (vector) f (transpose s))))))