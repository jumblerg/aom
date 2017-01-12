(ns hoplon.aom.elems
  (:require
    [hoplon.core :as dom :refer [defelem]]
    [hoplon.aom.utils    :refer [typestr bind-in!]]
    [javelin.core        :refer [with-let]]))

;;; elems ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(deftype Elem [ctor attrs elems]
  Object
  (toString [_]
    (apply str (mapcat (fn [[k v]] [k " " v]) attrs)))
  IPrintWithWriter
  (-pr-writer [this w _]
    (write-all w (typestr "hoplon.aom.elems/Elem" this)))
  IAssociative
  (-assoc [this k v]
    (Elem. ctor (assoc attrs k v) elems))
  IFn
  (-invoke [this k]
    (-lookup this k))
  (-invoke [this k nf]
    (-lookup this k nf))
  ILookup
  (-lookup [this k]
    (-lookup this k nil))
  (-lookup [this k nf]
    (-lookup attrs k nf))
  hoplon.core/INode
  (node [this]
    (with-let [e (ctor attrs elems)]
      (doseq [[a v] attrs]
        (hoplon.core/-attr! a e v)))))

;;; element middlewares ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn position [ctor value] ;; arrainge
  (fn [attrs elems]
    (with-let [e (ctor attrs elems)]
      (doseq [e (array-seq (.-children e))]
        (bind-in! e [.-style .-position] value))
      (bind-in! e [.-style .-boxSizing] "border-box")
      (bind-in! e [.-style .-display]   "inline-block")
      (bind-in! e [.-style .-position]  "relative"))))

;;; elems ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defelem flow [attrs elems]
  (Elem. (position dom/div "relative") attrs elems))

(defelem pile [attrs elems]
  (Elem. (position dom/div "absolute") attrs elems))
