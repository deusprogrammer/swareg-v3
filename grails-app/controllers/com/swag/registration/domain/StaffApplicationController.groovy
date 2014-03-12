package com.swag.registration.domain

import grails.plugins.springsecurity.SpringSecurityService
import grails.plugins.springsecurity.Secured
import com.swag.registration.security.acl.EventService

@Secured(['ROLE_USER'])
class StaffApplicationController {
    EventService eventService
    SpringSecurityService springSecurityService
    
    def view(Long id) {
        StaffApplication application = StaffApplication.get(id)
        
        if (!application) {
            response.sendError(404)
            return
        }
        
        eventService.checkRead(application)
        
        [application: application]
    }
    
    def approve(Long id) {
        StaffApplication application = StaffApplication.get(id)
        
        if (!application) {
            response.sendError(404)
            return
        }
        
        eventService.checkAdmin(application)
        
        application.approve(springSecurityService.getCurrentUser())
        
        redirect(controller: "dashboard", action: "manageStaff", id: application.owner.id)
    }
    
    def decline(Long id) {
        StaffApplication application = StaffApplication.get(id)
        
        eventService.checkAdmin(application)
        
        application.decline()
        
        redirect(controller: "dashboard", action: "manageStaff", id: application.owner.id)
    }
}