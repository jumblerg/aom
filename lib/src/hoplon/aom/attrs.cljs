(ns hoplon.aom.attrs
  (:require
    [hoplon.core      :refer [ICustomAttribute]]
    [hoplon.aom.utils :refer [bind-update-in!]])
  (:require-macros
    [hoplon.aom.attrs :refer [defattr]]))

;;; protocols ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defprotocol IFill
  (-fill [this]))

(defprotocol IDistance
  (-dist [this]))

;;; layout/sizing ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defattr s  [e v]
  #_{:pre [(v/size? v)]}
  (bind-update-in! e [.-style .-width]  -dist v)
  (bind-update-in! e [.-style .-height] -dist v))

(defattr sh [e v]
  #_{:pre [(v/size? v)]}
  (bind-update-in! e [.-style .-width]  -dist v))

(defattr sv [e v]
  #_{:pre [(v/size? v)]}
  (bind-update-in! e [.-style .-height] -dist v))

;;; layout/positioning ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defattr p [e v]
  (bind-update-in! e [.-style .-padding] -dist v))

;;; styles ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defattr fc [e v]
  (bind-update-in! e [.-style .-background] -fill v))
