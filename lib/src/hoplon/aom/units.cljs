(ns hoplon.aom.units
  (:require
    [hoplon.aom.attrs  :as attr]
    [hoplon.aom.colors :as color]
    [hoplon.aom.utils  :as util]))

;;; you-no-can-have css types ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(extend-type number
  attr/IDistance
  (-dist [this]
    (str this "px"))
  attr/IFill
  (-fill [this]
    (apply util/cssfn "rgb" (color/num->rgb this)))
  color/IColor
  (-color [this]
    (apply util/cssfn "rgb" (color/num->rgb this)))
  color/IAngle
  (-angle [this]
    this)
  (-unit-angle [this]
    (str this "deg"))
  color/IDistance
  (-distance [this]
    (str this "px")))

(deftype Length [value unit]
  Object
  (toString [_]
    (str value unit))
  IPrintWithWriter
  (-pr-writer [this w _]
    (write-all w (util/typestr "hoplon.aom.attrs/Length" this)))
  attr/IDistance
  (-dist [this]
    this)
  color/IDistance
  (-distance [this]
    this))

(deftype Percentage [value]
  Object
  (toString [_]
    (str value "%"))
  IPrintWithWriter
  (-pr-writer [this w _]
    (write-all w (util/typestr "hoplon.aom.attrs/Percentage" this)))
  attr/IDistance
  (-dist [this]
    this)
  color/IAngle
  (-angle [this]
    this)
  (-unit-angle [_]
      (str (/ value 100) "turn"))
  color/IDistance
  (-distance [this]
    this)
  color/IPercentage
  (-percentage [this]
    this)
  color/IOpacity
  (-opacity [_]
    (/ value 100)))

;;; units ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn px [v] (Length. v "px"))
(defn % ([v] (Percentage. v)) ([n d] (% (*  (/ n d) 100))))

