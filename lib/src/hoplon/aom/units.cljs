(ns hoplon.aom.units
  (:require
    [hoplon.aom.attrs :refer [ISize]]
    [hoplon.aom.utils :refer [typestr]]))

;;; you-no-can-have css types ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(deftype Length [value unit]
  Object
  (toString [_]
    (str value unit))
  IPrintWithWriter
  (-pr-writer [this w _]
    (write-all w (typestr "Length" this)))
  ISize
  (-size [this]
    this))

(deftype Percentage [value]
  Object
  (toString [_]
    (str value "%"))
  IPrintWithWriter
  (-pr-writer [this w _]
    (write-all w (typestr "Percentage" this)))
  ISize
  (-size [this]
    this))

;;; constructors ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn px [v] (Length. v "px"))
(defn % ([v] (Percentage. v)) ([n d] (% (*  (/ n d) 100))))

