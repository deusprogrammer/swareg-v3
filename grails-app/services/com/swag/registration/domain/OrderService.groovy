package com.swag.registration.domain

import com.swag.registration.domain.order.*
import com.swag.registration.security.User
import com.trinary.paypal.*
import com.trinary.paypal.rest.*
import com.trinary.paypal.oauth.*
import com.trinary.paypal.payment.*
import com.trinary.paypal.payment.payer.*

import com.trinary.paypal.payment.Currency as PayPalCurrency

class OrderService {
	def grailsApplication
	
	public Map executePayPalPayment(PayPalOrder order, String payerId) {
		PaymentRequest paymentRequest = new PaymentRequest()
		PaymentResponse paymentResponse = paymentRequest.execute(order.paymentId, payerId)
		
		if (!paymentResponse) {
			return [
				success: false,
				message: "Payment failed!",
				redirectUrl: "",
				shipping: [
					line1: "",
					line2: "",
					city: "",
					state: "",
					zipCode: "",
					countryCode: ""
				],
				error: [
					errorType: paymentRequest.errors.name,
					message: paymentRequest.errors.message,
					details: paymentRequest.errors.details.collect{it.toString()}
				]
			]
		} else {
			return [
				success: true,
				message: "Payment successful!",
				redirectUrl: "",
				shipping: [
					line1: paymentResponse.payer.payerInfo?.shippingAddress?.line1,
					line2: paymentResponse.payer.payerInfo?.shippingAddress?.line2,
					city: paymentResponse.payer.payerInfo?.shippingAddress?.city,
					state: paymentResponse.payer.payerInfo?.shippingAddress?.state,
					zipCode: paymentResponse.payer.payerInfo?.shippingAddress?.postalCode,
					countryCode: paymentResponse.payer.payerInfo?.shippingAddress?.countryCode
				],
				error: [
					errorType: "",
					message: "",
					details: []
				]
			]
		}
	}
	
	public Map payWithPayPal(PayPalOrder order, String returnUrl, String cancelUrl) {
		String clientId = ConfigHolder.getConfig('payPal.clientId')
		String secret   = ConfigHolder.getConfig('payPal.secret')
		println "CLIENT_ID: ${clientId}"
		println "SECRET:    ${secret}"
		println "ALL: ${ConfigHolder.list()}"
		
		PayPalConfig.setClientId(clientId)
		PayPalConfig.setSecret(secret)
		if (grailsApplication.config.payPal.debug) {
			println "DEBUG ENABLED"
			PayPalConfig.enableSandbox()
		}
		
		PayPalCurrency currency = PayPalCurrency.valueOf(order.currency.currencyCode)
		
		// PayPal Payment
		PaymentRequest paymentRequest = new PaymentRequest([
			intent: Intent.SALE,
			payer: new PayPalPayer(),
			redirectUrls: new RedirectUrls([
				returnUrl: returnUrl,
				cancelUrl: cancelUrl
			])
		])
		
		Transaction transaction = new Transaction([
			amount: new Amount([
				currency: currency,
				details: new Details()
			]),
			itemList: new ItemList(),
			taxRate: order.taxRate
		])
		
		order.badges.each { RegistrationOrderItem badge ->
			Double price = badge.price
			Integer quantity = badge.quantity
			String description = badge.description
			
			transaction.addItem(new Item([
				name: description,
				price: price,
				currency: currency,
				quantity: quantity
			]))
		}
		
		println "AMOUNT   ${transaction.amount}"
		println "DETAILS  ${transaction.amount.details}"
		println "SUBTOTAL ${transaction.amount.details.subtotal}"
		println "TAXRATE  ${order.taxRate}"
		println "CURRENCY ${currency}"
		transaction.amount.details.setTax(order.taxRate * transaction.amount.details.subtotal)
		
		paymentRequest.addTransaction(transaction)

		PaymentResponse paymentResponse = paymentRequest.pay()
		
		if (!paymentResponse) {
			return [
				success: false,
				message: "An error occurred processing your order.",
				redirectUrl: "",
				shipping: [
					line1: "",
					line2: "",
					city: "",
					state: "",
					zipCode: "",
					countryCode: ""
				],
				error: [
					errorType: paymentRequest.errors.name,
					message: paymentRequest.errors.message,
					details: paymentRequest.errors.details.collect{it.toString()}
				]
			]
		}
		
		if (paymentResponse.state != "created") {
			return [
				success: false,
				message: "Payment creation failed!",
				redirectUrl: "",
				shipping: [
					line1: "",
					line2: "",
					city: "",
					state: "",
					zipCode: "",
					countryCode: ""
				],
				error: [
					type: "",
					message: "",
					details: []
				]
			]
		} else {
			order.paymentId = paymentResponse.id
			
			return [
				success: true,
				message: "Payment pending",
				redirectUrl: paymentResponse.links.find {Link link->
					link.rel == "approval_url"
				}.href,
				shipping: [
					line1: "",
					line2: "",
					city: "",
					state: "",
					zipCode: "",
					countryCode: ""
				],
				error: [
					type: "",
					message: "",
					details: []
				]
			]
		}
	}
}
