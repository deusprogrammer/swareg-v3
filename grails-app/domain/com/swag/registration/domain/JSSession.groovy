package com.swag.registration.domain

import groovy.time.TimeCategory
import java.util.UUID

class JSSession {
    String token
    Date expires
    String jsonCookieData

    def beforeInsert = {
        def now = new Date()
        token = UUID.randomUUID() as String

        use(TimeCategory) {
            expires = now + 8.hours
        }
    }

    def beforeUpdate = {

    }

    static constraints = {
        token          nullable: true
        expires        nullable: true
        jsonCookieData nullable: true
    }
}
