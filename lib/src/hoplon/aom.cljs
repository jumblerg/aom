(ns hoplon.aom
  (:require
    [hoplon.core :as dom :refer [defelem]]))

(deftype Elem [attrs elems]
  hoplon.core/INode
  (node [_]
    (dom/div attrs elems)))

(defelem elem [attrs elems]
  (Elem. attrs elems))
