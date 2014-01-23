package com.swag.registration.domain

import com.swag.registration.security.acl.EventService
import grails.plugins.springsecurity.SpringSecurityService
import grails.plugins.springsecurity.Secured

@Secured(['ROLE_USER'])
class StaffPositionController {
	EventService eventService
	SpringSecurityService springSecurityService
	
	def create(Long id) {
		Event event = Event.get(id)
		
		if (!event) {
			response.setStatus(404)
			return
		}
		
		eventService.checkAdmin(event)
		
		[event: event]
	}
	
	def save() {
		StaffPosition position = new StaffPosition(params)
		
		eventService.checkAdmin(position)
		
		int permissionsMask = 0
		if (params.read) {
			permissionsMask += 1
		}
		if (params.write) {
			permissionsMask += 2
		}
		if (params.admin) {
			permissionsMask += 4
		}
		position.permissions = permissionsMask
		
		if (!position.save()) {
			flash.message = "Unable to create position!"
			redirect(action: "create")
			return
		}
		
		// Apply permissions mask using springSecurityAclService
				
		redirect(controller: "dashboard", action: "manageStaff", id: position.event.id)
	}
	
	def modify(Long id) {
		StaffPosition position = StaffPosition.get(id)
		
		if (!position) {
			response.setStatus(404)
			return
		}
		
		eventService.checkAdmin(position)
		
		// Unpack permissions mask
		
		[position: position]
	}
	
	def update(Long id) {
		StaffPosition position = StaffPosition.get(id)
		
		if (!position) {
			response.setStatus(404)
			return
		}
		
		eventService.checkAdmin(position)
		
		position.properties = params
		
		int permissionsMask = 0
		if (params.read) {
			permissionsMask += 1
		}
		if (params.write) {
			permissionsMask += 2
		}
		if (params.admin) {
			permissionsMask += 4
		}
		position.permissions = permissionsMask
		
		if (!position.save()) {
			flash.message = "Unable to modify position!"
			redirect(action: "modify", id: id)
			return
		}
		
		redirect(controller: "dashboard", action: "manageStaff", id: position.event.id)
	}
	
	def apply(Long id) {
		StaffPosition position = StaffPosition.get(id)
		
		if (!position) {
			response.setStatus(404)
			return
		}
		
		[position: position]
	}
	
	def process(Long id) {
		StaffPosition position = StaffPosition.get(id)
		
		if (!position) {
			response.setStatus(404)
			return
		}
		
		if (!position.apply(springSecurityService.getCurrentUser(), params.message)) {
			flash.message = "Unable to process application!"
			redirect(action: "apply", id: id)
			return
		}
		
		[position: position]
	}
}
