(ns euler.core
  (:gen-class))

(defn multiple? [n m]
  "Is `m` a multiple of `n`?"
  (zero? (rem m n))
  )

(defn e01-multiples-of-3-or-5 []
  "the sum of all the multiples of 3 or 5 below 1000"
  (reduce + (filter (some-fn (partial multiple? 3)
                             (partial multiple? 5))
                    (range 1 1000))))

(defn fib
  "Lazy sequence of Fibonacci numbers."
  ([]
   (fib 1 1))
  ([a b]
   (lazy-seq (cons a (fib b (+ a b))))))

(defn fib-not-exceeding [n]
  "Fibonacci sequence of terms not exceeding `n`"
  (take-while (partial >= n) (fib)))

(defn e02-even-fibonacci-numbers []
  (reduce + (filter even? (fib-not-exceeding 4000000))))

(defn sieve []
  "Sieve of Eratosthenes, hopelessly inefficient but very fun."
  (map first (iterate
              (fn [[p candidates]]
                (let [p1 (first candidates)
                      candidates' (rest candidates)]
                  [p1
                   (filter (fn [x] (not (multiple? p1 x)))
                           candidates')]))
              [2 (iterate (fn [x] (+ 2 x)) 3)]
              )))

(def primes (sieve))

(defn prime-factors [n]
  "Return list of prime factors of `n`."
  (loop [q n
         factors nil
         candidates primes
         ]
    (if (= q 1)
      factors
      (let [p (first candidates)]
        (if (multiple? p q)
          (recur (quot q p) (cons p factors) candidates)
          (recur q factors (rest candidates))
          )))
    )
  )

(defn e03-largest-prime-factor []
  (first (prime-factors 600851475143)))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
