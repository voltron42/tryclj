(ns tryclj.routings-test
  (:require [clojure.test :refer :all]
            [tryclj.routings :refer :all]
            [ring.mock.request :as mock]
            ))

(deftest test-submit
  (let [app (build-app)
        base "http://localhost:5000"
        path "/repl"
        mock-request (->
                       (mock/request :post (str base path))
                       (mock/body "(+ 1 2)"))
        {:keys [body]} (app mock-request)
        response (slurp body)]
    (println response)))

(deftest test-submit-error
  (let [app (build-app)
        base "http://localhost:5000"
        path "/repl"
        mock-request (->
                       (mock/request :post (str base path))
                       (mock/body "hi"))
        {:keys [body]} (app mock-request)
        response (slurp body)]
    (println response)))