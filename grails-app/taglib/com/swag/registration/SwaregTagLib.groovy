package com.swag.registration

import grails.plugins.springsecurity.SpringSecurityService
import com.swag.registration.domain.Event
import com.synergyj.grails.plugins.avatar.util.MD5Util

class SwaregTagLib {
    SpringSecurityService springSecurityService
	
	def gravatar = { attrs, body ->
	    String email = springSecurityService.currentUser.emailAddress
	    def hash = MD5Util.md5Hex(email)
	    def size = attrs.remove('size') ?: 20
	    def alt = attrs.remove('alt') ?: "Gravatar"
	    def cssClass = attrs.remove('cssClass') ?: "avatar"
	    def title = attrs.remove('title') ?: ''
	    def id = attrs.remove('id') ?: ''
	    def name = attrs.remove('name') ?: ''
	    def dgu = (attrs.remove('defaultGravatarUrl') ?: grailsApplication.config.avatarPlugin.defaultGravatarUrl) ?: ''
	    def gravatarRating = attrs.remove('gravatarRating') ?: grailsApplication.config.avatarPlugin.gravatarRating
	    def secureGravatar = attrs.remove('secure') ?: grailsApplication.config.avatarPlugin.gravatarSecure
	
	    def gravatarBaseUrl = (secureGravatar ?: request.isSecure()) ? "https://secure.gravatar.com/avatar/" : "http://gravatar.com/avatar/"
	    String gravatarUrl = "$gravatarBaseUrl$hash"
	    gravatarUrl += dgu.matches(/404|mm|identicon|monsterid|wavatar|retro|http.*/) ? "?d=${dgu}" : ''
	
	    if (gravatarRating) {
	      gravatarUrl += (gravatarUrl.contains('?') ? '&' : '?') + "r=${gravatarRating}"
	    }
	
	    // The size is requested to gravatar in order to get the imaged in the requested size
	    // If we don't send the 's' parameter the image is received at 80x80
	    def validGravatarSize = 1..512
	    if (validGravatarSize.contains(size.toInteger())) {
	      gravatarUrl += "&s=${size}"
	    }
	
	    out << """<img id="${id}" name="${name}" alt="$alt" class="$cssClass" height="$size" width="$size" src="$gravatarUrl" title="$title"/>"""
	}
}
