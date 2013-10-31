package com.swag.registration.domain.order

import com.swag.registration.domain.*
import com.swag.registration.security.User;

class RegistrationOrderItem implements Serializable, Payable {
	Integer quantity
	RegistrationLevel registrationLevel
	
	static belongsTo = [order: PayPalOrder]
	
	public Double getPrice() {
		return registrationLevel.currentPrice
	}
	
	public String getDescription() {
		return registrationLevel.description
	}
	
    static constraints = {
    }

	@Override
	public Boolean isPaid() {
		order.paymentCompleted
	}

	@Override
	public Double getTotal() {
		return price
	}

	@Override
	public Double getTax() {
		return 0.00
	}

	@Override
	public Double getTaxRate() {
		return order.taxRate
	}

	@Override
	public Currency getCurrency() {
		return order.currency
	}

	@Override
	public User getPurchaser() {
		return order.user
	}}
