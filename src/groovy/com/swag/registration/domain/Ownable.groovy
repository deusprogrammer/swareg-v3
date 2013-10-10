package com.swag.registration.domain

import com.swag.registration.security.User

interface Ownable {
    User getOwner()
}
