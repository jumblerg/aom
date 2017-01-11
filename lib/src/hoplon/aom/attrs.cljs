(ns hoplon.aom.attrs
  (:require
    [hoplon.core      :refer [ICustomAttribute]]
    [hoplon.aom.utils :refer [bind-update-in!]])
  (:require-macros
    [hoplon.aom.attrs :refer [defattr]]))

;;; protocols ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defprotocol IAlpha
  (-alpha [this]))

(defprotocol ISize
  (-size [this]))

(defn alpha? [v] (satisfies? IAlpha v))
(defn size?  [v] (satisfies? ISize v))

;;; layout/sizing ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defattr s  [e v]
  #_{:pre [(v/size? v)]}
  (bind-update-in! e [.-style .-width]  -size v)
  (bind-update-in! e [.-style .-height] -size v))

(defattr sh [e v]
  #_{:pre [(v/size? v)]}
  (bind-update-in! e [.-style .-width]  -size v))

(defattr sv [e v]
  #_{:pre [(v/size? v)]}
  (bind-update-in! e [.-style .-height] -size v))

;;; layout/positioning ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defattr p [e v]
  (bind-update-in! e [.-style .-padding] -size v))
