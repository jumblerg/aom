(ns hoplon.aom.utils)

(defmacro set-in! [elem path value]
  `(set! ~(reduce #(list %2 %1) elem path) ~value))

(defmacro bind-in! [elem path value]
  `(bind-with! (fn [v#] (set-in! ~elem ~path v#)) ~value))

(defmacro bind-update-in! [elem path f value]
  `(bind-with! (fn [v#] (set-in! ~elem ~path (~f v#))) ~value))
