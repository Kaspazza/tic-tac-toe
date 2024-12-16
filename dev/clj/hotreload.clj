(ns hotreload
  (:require [ring.adapter.jetty :refer [run-jetty]]
            [ring.middleware.reload :refer [wrap-reload]]
            [shadow.cljs.devtools.server :as server]
            [shadow.cljs.devtools.api :as shadow]
            [kaspazza.tic-tac-toe :refer [handler]])
  (:gen-class))

(def dev-handler
  (wrap-reload #'handler))

(defn -main [& _args]
  (future (run-jetty dev-handler {:port 13000}))
  (server/start!)
  (shadow/watch :frontend)
  )
