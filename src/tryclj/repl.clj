(ns tryclj.repl)

(defmacro with-out-str-data-map
  [& body]
  `(let [s# (new java.io.StringWriter)]
     (binding [*out* s#]
       (let [r# ~@body]
         {:result r#
          :console (str s#)}))))

(defn run [code]
  (try
    (let [expression (read-string code)
          {:keys [console] :as output} (with-out-str-data-map (eval expression))]
      (assoc output :console (clojure.string/split console #"\r\n")))
    (catch Exception e
      {:error e})))