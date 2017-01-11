(ns hoplon.aom.utils
  (:require
    [clojure.string :refer [split]]
    [cljs.reader    :refer [read-string]]
    [javelin.core   :refer [cell? with-let]])
  (:require-macros
    [hoplon.aom.utils]))

(defn typestr [type value]
  (str "#<" *ns* "/" type value ">"))

(defn- bind-with! [f value]
  (if (cell? value) (f @(add-watch value (gensym) #(f %4))) (f value)))

(defn route->hash [[path & [qmap]]]
  "transforms a urlstate of the form [[\"foo\" \"bar\"] {:baz \"barf\"}]
   to hash string in the form \"foo/bar&baz=barf\""
  (let [pair (fn [[k v]] (str (name k) "=" (pr-str v)))
        pstr (when (not-empty path) (apply str "/" (interpose "/" (map name path))))
        qstr (when (not-empty qmap) (apply str "?" (interpose "&" (map pair qmap))))]
    (str pstr qstr)))

(defn hash->route [hash]
  "transforms a hash string to a urlstate of the form
   [[\"foo\" \"bar\"] {:baz \"barf\"}]"
  (let [[rstr qstr] (split (subs hash 1) #"\?")
        pair        #(let [[k v] (split % #"=" 2)] [(keyword k) (read-string v)])
        qmap        (->> (split qstr #"&") (map pair) (when (not-empty qstr)) (into {}))
        path        (->> (split rstr #"/") (remove empty?) (mapv keyword))]
    (vec [path qmap])))

(defn clean [map]
  "remove nil keys from the map"
  (into {} (filter second map)))

(defn copy! [text]
  "copy the text to the user's clipboard"
  (let [foc (.-activeElement js/document)
        tgt (with-let [e (.createElement js/document "textarea")]
              (set! (.-value e) text))]
    (.appendChild (.-body js/document) tgt)
    (.focus tgt)
    (.setSelectionRange tgt 0 (.. tgt -value -length))
    (.execCommand js/document "copy")
    (.focus foc)
    (.removeChild (.-body js/document) tgt)))

(defn debounce [ms f]
  (let [id (atom nil)]
    (fn [& args]
      (js/clearTimeout @id)
      (reset! id (js/setTimeout #(apply f args) ms)))))
