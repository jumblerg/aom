(ns hoplon.aom.attrs)

(defmacro defattr [name args & forms]
  `(def ~name (reify hoplon.core/ICustomAttribute
     (hoplon.core/-attr! [_# ~@args] ~@forms))))
