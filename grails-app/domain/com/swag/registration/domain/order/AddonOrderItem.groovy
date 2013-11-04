package com.swag.registration.domain.order

import com.swag.registration.domain.*

class AddonOrderItem implements Serializable {
    Integer quantity
    Addon addon

    static constraints = {
    }
}
