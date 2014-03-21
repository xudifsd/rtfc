(ns rtfc.core
  (:require [clojure.pprint :as pp])
  (:import [clojure.lang MapEntry]))

(defn inspect [msg x]
  (println msg)
  (pp/pprint x)
  x)

(defn ignore
  ([& _] 'ignored))

(defn pprint-all
  ([v] (println v "got no args"))
  ([v & args]
   (loop [arg args
           n 0]
     (if (empty? arg)
       nil
       (do
         (println n "arg for" v "is")
         (pp/pprint arg)
         (recur (rest arg) (inc n)))))))

(defn print-st [& _]
  (try
    (throw (Exception. "dummy"))
    (catch Exception e
      (.printStackTrace e))))

; map of var to their orig binding
(def var-roots (atom {}))

(defn trace [v f]
  (alter-var-root v (fn [orig]
                      (when-not (get @var-roots v)
                        (swap! var-roots assoc v orig))
                      (fn [& all]
                        (apply f v all)
                        (let [result (apply orig all)]
                          (println "return value for" v "is:")
                          (pp/pprint result)
                          (println))))))

(defn untrace
  ([v] (alter-var-root v (fn [_]
                      (get @var-roots v))))
  ([v & _] (untrace v)))

(defn untrace-all
  ([] (untrace-all nil))
  ([_] ; makes it easy to change trace call into untrace call
   (doseq [e @var-roots]
     (untrace (.key ^MapEntry e)))))
