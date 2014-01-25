package com.swag.registration.domain

import com.swag.registration.security.*
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
				
		redirect(controller: "dashboard", action: "manageStaff", id: position.event.id)
	}
	
	def modify(Long id) {
		StaffPosition position = StaffPosition.get(id)
		
		if (!position) {
			response.setStatus(404)
			return
		}
		
		eventService.checkAdmin(position)
		
		Map permissions = [:]
		List bits = []
		
		Byte mask = 0x01
		
		Byte p = position.permissions
		
		4.times {
			bits.add(0)
		}
		
		Integer i = 0
		while (p > 0) {
			Byte b = p & mask
			bits.set(i, b)
			p = p >> 1
			i++
		}
		
		permissions["read"]  = bits.get(0) == 1
		permissions["write"] = bits.get(1) == 1
		permissions["admin"] = bits.get(2) == 1
		
		[event: position.event, position: position, permissions: permissions]
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
	
	def assign(Long id) {
		StaffPosition position = StaffPosition.get(id)
		
		if (!position) {
			response.setStatus(404)
			return
		}
		
		[position: position]
	}
	
	def doAssign(Long id) {
		String email = params.emailAddress
		StaffPosition position = StaffPosition.get(id)
		
		if (!position) {
			response.setStatus(404)
			return
		}
		
		eventService.checkAdmin(position)
		
		User user = User.findByEmailAddress(email)
		
		if (!user) {
			Activation activation = Activation.create(user)
			user = activation.user
		}
		
		if (!position.assign(user)) {
			flash.message = "Unable to unassign position!"
		}
		
		redirect(controller: "dashboard", action: "manageStaff", id: position.event.id)
	}
	
	def unassign(Long id) {
		StaffPosition position = StaffPosition.get(id)
		
		if (!position) {
			response.setStatus(404)
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
			response.setStatus(404)
			return
		}
		
		eventService.checkAdmin(position)
		
		if (!position.delete()) {
			flash.message = "Unable to delete position!"
		}
		
		redirect(controller: "dashboard", action: "manageStaff", id: event.id)
	}
}