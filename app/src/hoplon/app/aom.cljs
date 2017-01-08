(ns ^{:hoplon/page "index.html"} hoplon.app.aom
  (:refer-clojure
    :exclude [- test])
  (:require
    [javelin.core :refer [defc defc= cell= cell]]
    [hoplon.core  :refer [defelem for-tpl when-tpl case-tpl]]
    [hoplon.aom   :refer [elem]]))

(hoplon.core/html
  (hoplon.core/head
    (hoplon.core/title "hello"))
  (hoplon.core/body :css/margin 0
    (elem "hello application object model")))

