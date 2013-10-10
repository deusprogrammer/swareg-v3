package com.swag.registration.domain

import grails.converters.JSON

class JSSessionService {

    def createSession() {
        def jsess = new JSSession()
        jsess.save(flush: true)

        return jsess.token
    }

    def getSessionData(def token, def key) {
        def jsess = JSSession.findByToken(token)
        if (jsess.jsonCookieData) {
            return JSON.parse(jsess.jsonCookieData)[key]
        }
        else {
            return null
        }
    }

    def setSessionData(def token, def key, def value) {
        def jsess = JSSession.findByToken(token)
        def map = [:]
        if (jsess.jsonCookieData) {
            map = JSON.parse(jsess.jsonCookieData)
        }
        map[key] = value
        jsess.jsonCookieData = map as JSON
        jsess.save(flush: true)
    }

    def isSessionValid(def token) {
        def jsess = JSSession.findByToken(token)
        def now = new Date()

        if (!jsess || now > jsess.expires) {
            return false
        }
        else {
            return true
        }
    }
}
