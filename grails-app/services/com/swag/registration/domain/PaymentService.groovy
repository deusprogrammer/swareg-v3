package com.swag.registration.domain

import com.swag.registration.security.User
import com.trinary.paypal.*
import com.trinary.paypal.oauth.*
import com.trinary.paypal.payment.*
import com.trinary.paypal.payment.payer.*
import com.trinary.paypal.rest.PaymentRequest;
import com.trinary.paypal.rest.PaymentResponse;

class PaymentService {
	def grailsApplication

    public Map payWithCreditCard(Payable payableObject, String creditCardNumber, String cvv2, String expireMonth, String expireYear, String cardType) {
		String clientId = ConfigHolder.getConfig('payPal.clientId')
		String secret   = ConfigHolder.getConfig('payPal.secret')
		println "CLIENT_ID: ${clientId}"
		println "SECRET:    ${secret}"
		println "ALL: ${ConfigHolder.list()}"
		
		PayPalConfig.setClientId(clientId)
		PayPalConfig.setSecret(secret)
		//if (ConfigHolder.getConfig("paypal.debug") == "true") {
		if (grailsApplication.config.payPal.debug) {
			println "DEBUG ENABLED"
			PayPalConfig.enableSandbox()
		}
		
		User user = payableObject.getPurchaser()
		Double price = payableObject.getPrice()
		Double taxRate = payableObject.getTaxRate()
		com.trinary.paypal.payment.Currency currency = com.trinary.paypal.payment.Currency.valueOf(payableObject.getCurrency().getCurrencyCode())
		Double tax = (price * taxRate).round(2)
		String last4 = creditCardNumber[-4..-1]
		
		String firstName = user.firstName
		String lastName  = user.lastName
		
		if (payableObject.isPaid()) {
			return [
				success: false, 
				message: "This item is already paid for!", 
				ccNumber: last4, 
				receiptNumber: "", 
				status: "",
				error: [
					type: "",
					details: []
				]
			]
		}
		
		CreditCardPayer payer = new CreditCardPayer()
		CreditCard creditCard = new CreditCard([
			number: creditCardNumber,
			type: CreditCardType.valueOf(cardType.toUpperCase()),
			expireMonth: expireMonth.toInteger(),
			expireYear: expireYear.toInteger(),
			cvv2: cvv2,
			firstName: firstName,
			lastName: lastName,
			billingAddress: new BillingAddress([
				line1: user.streetAddress1,
				line2: user.streetAddress2,
				city: user.city,
				countryCode: "US",
				postalCode: user.zipCode,
				state: user.state
			])
		])

		payer.addFundingInstrument(creditCard)

		PaymentRequest paymentRequest = new PaymentRequest([
			intent: Intent.SALE,
			payer: payer
		])
		paymentRequest.addTransaction(new Transaction([
			amount: new Amount([
				currency: currency,
				details: new Details([
					subtotal: price,
					tax: tax
				])
			]),
			description: payableObject.getDescription()
		]))

		PaymentResponse paymentResponse = paymentRequest.pay()
		
		if (!paymentResponse) {
			return [
				success: false, 
				message: "An error occurred processing your order.", 
				ccNumber: last4, 
				receiptNumber: "", 
				status: "", 
				error: [
					errorType: paymentRequest.errors.name,
					message: paymentRequest.errors.message,
					details: paymentRequest.errors.details.collect{it.toString()}
				]
			]
		}
		
		if (paymentResponse.state != "approved") {
			return [
				success: false, 
				message: "Payment declined", 
				ccNumber: last4, 
				receiptNumber: "", 
				status: paymentResponse.state,
				error: [
					type: "",
					message: "",
					details: []
				]
			]
		} else {
			return [
				success: true, 
				message: "Payment approved!", 
				ccNumber: last4, 
				receiptNumber: paymentResponse.id, 
				status: paymentResponse.state,
				error: [
					type: "",
					message: "",
					details: []
				]
			]
		}
    }
}
