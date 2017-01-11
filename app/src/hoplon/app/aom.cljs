(ns ^{:hoplon/page "index.html"} hoplon.app.aom
  (:refer-clojure
    :exclude [- test])
  (:require
    [javelin.core :refer [cell cell= defc defc=]]
    [hoplon.core  :refer [defelem for-tpl when-tpl case-tpl]]
    [hoplon.aom.units :refer [%]]
    [hoplon.aom.attrs :refer [s sh sv]]
    [hoplon.aom.elems :refer [flow pile]]))

(hoplon.core/html :css/height "100%"
  (hoplon.core/head
    (hoplon.core/title "hello"))
  (hoplon.core/body :css/height "100%" :css/margin 0
    (flow s (% 1 1)
      (pile sh (% 1 2) sv (% 1 1)
        (flow s (% 1 1)
          "foo")
        (flow s (% 1 1)
          "bar"))
      (pile sh (% 1 2) sv (% 1 1)
        (flow s (% 1 1)
          "baz")
        (flow s (% 1 1)
          "barf")))))

