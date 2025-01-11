(ns euler.core
  (:gen-class))

(defn e01-multiples-of-3-or-5 []
  "the sum of all the multiples of 3 or 5 below 1000"
  (defn my-multiple? [x] (or (zero? (rem x 3))
                             (zero? (rem x 5))))
  (reduce + (filter my-multiple? (range 1 1000))))

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

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
