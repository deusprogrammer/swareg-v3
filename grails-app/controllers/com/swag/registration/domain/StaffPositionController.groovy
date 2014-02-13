package com.swag.registration.domain

import com.swag.registration.EmailService
import com.swag.registration.security.*
import com.swag.registration.security.acl.EventService
import grails.plugins.springsecurity.SpringSecurityService
import grails.plugins.springsecurity.Secured

@Secured(['ROLE_USER'])
class StaffPositionController {
	EventService eventService
	EmailService emailService
	SpringSecurityService springSecurityService
	
	def create(Long id) {
		Event event = Event.get(id)
		
		if (!event) {
			response.sendError(404)
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
				
		redirect(controller: "dashboard", action: "manageStaff", id: position.event.id)
	}
	
	def modify(Long id) {
		StaffPosition position = StaffPosition.get(id)
		
		if (!position) {
			response.sendError(404)
			return
		}
		
		eventService.checkAdmin(position)
		Map permissions = position.unpackPermissions()
		
		[event: position.event, position: position, permissions: permissions]
	}
	
	def update(Long id) {
		StaffPosition position = StaffPosition.get(id)
		
		if (!position) {
			response.sendError(404)
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
		
		position.updatePermissions()
		
		redirect(controller: "dashboard", action: "manageStaff", id: position.event.id)
	}
	
	def apply(Long id) {
		StaffPosition position = StaffPosition.get(id)
		
		if (!position) {
			response.sendError(404)
			return
		}
		
		[position: position]
	}
	
	def process(Long id) {
		StaffPosition position = StaffPosition.get(id)
		
		if (!position) {
			response.sendError(404)
			return
		}
		
		if (!position.apply(springSecurityService.getCurrentUser(), params.message)) {
			flash.message = "Unable to process application!"
			redirect(action: "apply", id: id)
			return
		}
		
		[position: position]
	}
	
	def assign(Long id) {
		StaffPosition position = StaffPosition.get(id)
		
		if (!position) {
			response.sendError(404)
			return
		}
		
		[position: position]
	}
	
	def doAssign(Long id) {
		String email = params.emailAddress
		StaffPosition position = StaffPosition.get(id)
		
		if (!position) {
			response.sendError(404)
			return
		}
		
		eventService.checkAdmin(position)
		
		User user = User.findByEmailAddress(email)
		Activation activation
		
		if (!user) {
			activation = Activation.create(email)
			user = activation.user
		} else if (!user.enabled) {
			activation = Activation.findByUser(user)
		}
		
		if (!position.assign(user)) {
			flash.message = "Unable to unassign position!"
		}
		
		redirect(controller: "dashboard", action: "manageStaff", id: position.event.id)
	}
	
	def unassign(Long id) {
		StaffPosition position = StaffPosition.get(id)
		
		if (!position) {
			response.sendError(404)
			return
		}
		
		eventService.checkAdmin(position)
		
		if (!position.unassign()) {
			flash.message = "Unable to unassign position!"
		}
		
		redirect(controller: "dashboard", action: "manageStaff", id: position.event.id)
	}
	
	def delete(Long id) {
		StaffPosition position = StaffPosition.get(id)
		Event event = position.event
		
		if (!position) {
			response.sendError(404)
			return
		}
		
		eventService.checkAdmin(position)
		
		if (!position.delete()) {
			flash.message = "Unable to delete position!"
		}
		
		redirect(controller: "dashboard", action: "manageStaff", id: event.id)
	}
}