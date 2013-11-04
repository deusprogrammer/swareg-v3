package com.swag.registration.security.acl

import com.swag.registration.security.User
import grails.plugins.springsecurity.SpringSecurityService
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper

class UserService {

    SpringSecurityService springSecurityService

    void accessCheck(User user) {
        if (!springSecurityService.currentUser ||
            user != springSecurityService.currentUser &&
            !SecurityContextHolderAwareRequestWrapper.isUserInRole("ROLE_GLOBAL")) {

            throw new AccessDeniedException("Access exception occured")
        }
    }
}
