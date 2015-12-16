(defproject com.keldoc/manifold "0.1.2-SNAPSHOT"
  :description "a compatibility layer for event-driven abstractions"
  :license {:name "MIT License"
            :url "http://opensource.org/licenses/MIT"}
  :url "https://github.com/ztellman/manifold"
  :dependencies [[org.clojure/tools.logging "0.3.1" :exclusions [org.clojure/clojure]]
                 [io.aleph/dirigiste "0.1.2-alpha1"]
                 [riddley "0.1.11"]]
  :profiles {:dev {:dependencies [[codox-md "0.2.0" :exclusions [org.clojure/clojure]]
                                  [org.clojure/clojure "1.8.0-RC1"]
                                  [criterium "0.4.3"]
                                  [org.clojure/core.async "0.2.374"]]}}
  :test-selectors {:default #(not
                               (some #{:benchmark :stress}
                                 (cons (:tag %) (keys %))))
                   :benchmark :benchmark
                   :stress #(or (:stress %) (= :stress (:tag %)))
                   :all (constantly true)}
  :plugins [[codox "0.8.10"]
            [lein-jammin "0.1.1"]
            [ztellman/lein-cljfmt "0.1.10"]]
  :cljfmt {:indents {#".*" [[:inner 0]]}}
  :codox {:src-dir-uri "https://github.com/ztellman/manifold/blob/master/"
          :src-linenum-anchor-prefix "L"
          :defaults {:doc/format :markdown}
          :include [manifold.deferred manifold.stream manifold.time manifold.bus manifold.executor]}
  :global-vars {*warn-on-reflection* true}
  :jvm-opts ^:replace ["-server"
                       "-XX:-OmitStackTraceInFastThrow"
                       "-XX:+UseConcMarkSweepGC"
                       "-Xmx2g"
                       "-XX:NewSize=1g"])
