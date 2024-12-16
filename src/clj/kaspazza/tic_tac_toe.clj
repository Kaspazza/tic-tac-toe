(ns kaspazza.tic-tac-toe
  (:require [ring.adapter.jetty :refer [run-jetty]]
            [reitit.ring :as rring]
            [reitit.coercion.spec]
            [reitit.ring.coercion :as rrc]
            [reitit.ring.middleware.muuntaja :as muuntaja]
            [reitit.ring.middleware.parameters :as parameters]
            [muuntaja.core :as m]
            )
  (:gen-class))

(def routes [["*" {:get (fn [_req] {:status 200
                                     :body "Hello World"})}]])

(def router (rring/router routes
                          {:data {:coercion   reitit.coercion.spec/coercion
                                  :muuntaja   m/instance
                                  :middleware [parameters/parameters-middleware
                                               rrc/coerce-request-middleware
                                               muuntaja/format-response-middleware
                                               rrc/coerce-response-middleware]}}))

(def handler
  (rring/ring-handler router))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (run-jetty handler {:port 3000}))


(comment

  (handler {:request-method :get, :uri "/favicon.ico"})

  (handler {:request-method :get, :uri "/ping"})

  (-main)


  ;
  )