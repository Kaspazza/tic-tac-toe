(ns kaspazza.main
  (:require [reagent.dom.client :as rdc]))

(defn main-component []
  [:div "hello! world"])

(defonce *root (atom nil))

(defn ^:after-load render!
  []
  (rdc/render @*root [main-component]))

(defn mount-root!
  [el-id]
  (try
    (let [root (-> el-id
                   js/document.getElementById
                   rdc/create-root)]
      (reset! *root root)
      (render!)) 
    (catch :default e (js/console.log (ex-info "Mount error" {:error e})))))

(defn ^:export init []
  (try
    (mount-root! "root")
    (catch :default e (js/console.log (ex-info "App init has failed" e)))))

(comment

  (tap> (+ 1 1))
  (+ 1 1)
  ;
  )