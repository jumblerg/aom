(ns hoplon.aom.colors
  (:require
    [hoplon.aom.attrs :as attr]
    [hoplon.aom.utils :as util]))

;;; utils ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn num->rgb [hex] (mapv #(bit-and (bit-shift-right hex %) 255) [16 8 0]))

;;; protocols ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defprotocol IAngle
  (-angle [this]))

(defprotocol IPercentage
  (-percentage [this]))

(defprotocol IInteger
  (-integer [this]))

(defprotocol IOpacity
  (-opacity [this]))

;;; types ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(deftype Color [system x y z a]
  Object
  (toString [_]
    (util/cssfn system x y z a))
  IPrintWithWriter
  (-pr-writer [this w _]
    (write-all w (util/typestr "hoplon.aom.colors/Color" this)))
  attr/IFill
  (-fill [this]
    this))

;;; colors ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn hsl
  "construct a color based on the cylindrical coordinate system from hue,
   saturation, lightness and an optional alphatransparancy parameter.

   the hue is the angle to the the color on the cylindrical rainbow where the
   top of the circle is red.  the saturation, lightness, and alpha are all
   percentages, where the saturation is the range of grey to full color, the
   lightness the range from black to white, and the alpha the opacity that
   determines how the color should composite with its background."
  ([h s l]
   (hsl h s l (reify IOpacity (-opacity [_] 1))))
  ([h s l a]
   (Color. "hsla" (-angle h) (-percentage s) (-percentage l) (-opacity a))))

(defn rgb
  "construct a color based on the cubic coordinate system from a red, blue,
   green and an optional alphatransparency parameter. the rgb color values may
   be expressed as intergers ranging from 0 to 255, percentages, or as a single
   hexidecimal value."
  ([hex]
   (rgb hex (reify IOpacity (-opacity [_] 1))))
  ([hex a]
   (apply rgb (conj (num->rgb hex) a)))
  ([r g b]
   (rgb r g b (reify IOpacity (-opacity [_] 1))))
  ([r g b a]
   (Color. "rgba" (-integer r) (-integer g) (-integer b) (-opacity a))))
