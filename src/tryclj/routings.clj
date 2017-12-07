(ns tryclj.routings
  (:require [compojure.api.sweet :as sweet]
            [compojure.api.resource :as resource]
            [compojure.api.core :as api]
            [schema.core :as s]
            [ring.util.http-response :as http]
            [ring.util.response :as resp]
            [tryclj.repl :as repl]
            [compojure.route :as route]))

(defn build-app []
  (sweet/routes
    (sweet/api {}
      (api/context "/repl" []
        (sweet/resource {:description ""
                         :post {:summary ""
                                :parameters {:body s/Any}
                               :responses {200 {:schema s/Any}}
                               :handler (fn [{body :body}]
                                          (let [code (slurp body)]
                                            (http/ok (repl/run code))))}}))
      (sweet/GET "/" [] (resp/redirect "/index.html")))
    (route/resources "/")
    (route/not-found "404 Not Found")))
