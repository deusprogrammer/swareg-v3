package com.swag.registration.domain

import com.swag.registration.security.User

interface Payable {
	Boolean isPaid()
    Double getPrice()
	Double getTotal()
	Double getTax()
    Double getTaxRate()
    Currency getCurrency()
    User getPurchaser()
    String getDescription()
}
