package com.swag.registration.domain

class EventSchedulerService {

    def sessionService

    def cloneEvent(def oldEventId, def year, def numeral) {
        def pastEvent = Event.get(oldEventId)

        if (!sessionService.canCloneEvent(oldEventId)) {
            println "User cannot clone event!"
            return [success: false]
        }

        if (!year && !numeral) {
            println "No year or numeral!"
            return [success: false]
        }

        if (pastEvent && !pastEvent.cloned) {
            def event = new Event(pastEvent.properties)
            event.numeral = numeral
            event.year = year ?: ""
            if (event.save(flush: true)) {
                println "Event cloned successfully!"
                pastEvent.cloned = true
                pastEvent.save(flush: true)
                return [success: true, event: event.id]
            }
            else {
                println "Unable to clone event!"
                return [success: false]
            }
        }
        else {
            println "Unable to clone event!"
            return [success: false]
        }
    }
}
