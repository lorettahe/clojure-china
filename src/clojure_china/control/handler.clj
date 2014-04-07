(ns clojure-china.control.handler
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [noir.session :as session]
            [compojure.core :refer :all]
            [clojure-china.pages.index :refer [index]]
            [clojure-china.pages.account.action :refer :all]
            [clojure-china.control.account.action :refer :all]))

(defroutes app-routes
           (GET "/" [] (index))
           (GET "/login" [] (login-page))
           (POST "/login" [user pwd] (user-login user pwd))
           (GET "/logout" [] (user-logout))
           (GET "/register" [] (register-page))
           (POST "/register" [username password r-password email] (user-register username password r-password email))
           (route/resources "/")
           (route/not-found "Not Found"))

(def app
  (->
    (handler/site app-routes)
    (session/wrap-noir-flash)
    (session/wrap-noir-session)))
