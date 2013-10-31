package com.swag.registration.domain

import com.swag.registration.security.User
import com.trinary.paypal.*
import com.trinary.paypal.rest.*
import com.trinary.paypal.oauth.*
import com.trinary.paypal.payment.*
import com.trinary.paypal.payment.payer.*

class PaymentService {
	def grailsApplication
	
	public Map executePayPalPayment(Payment payment, String payerId) {
		PaymentRequest paymentRequest = new PaymentRequest()
		PaymentResponse paymentResponse = paymentRequest.execute(payment.paymentId, payerId)
		
		if (!paymentResponse) {
			return [
				success: false,
				message: "Payment failed!",
				ccNumber: "PAYPAL",
				receiptNumber: "",
				transactionId: "",
				status: "",
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
				ccNumber: "PAYPAL",
				receiptNumber: paymentResponse.id,
				transactionId: "",
				status: paymentResponse.state,
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
	
	public Map payWithPayPal(ArrayList<Payable> payableObjects, Double taxRate, Currency currency, String returnUrl, String cancelUrl) {
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
		
		com.trinary.paypal.payment.Currency saleCurrency = com.trinary.paypal.payment.Currency.valueOf(currency.getCurrencyCode())
		
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
				currency: saleCurrency,
				details: new Details([
					tax: tax
				])
			])
		])
		
		payableObjects.each { Payable payableObject ->
			if (payableObject.isPaid()) {
				return [
					success: false,
					message: "This item is already paid for!",
					ccNumber: "PAYPAL",
					receiptNumber: "",
					transactionId: "",
					status: "",
					redirectUrl: "",
					error: [
						type: "",
						details: []
					]
				]
			}
			
			Double price = payableObject.getPrice()
			Double tax = payableObject.getTax().round(2)
			String description = payableObject.getDescription()
			
			transaction.addItem(new Item([
				name: description,
				price: price
			]))
		}
		
		paymentRequest.addTransaction(transaction)

		PaymentResponse paymentResponse = paymentRequest.pay()
		
		if (!paymentResponse) {
			return [
				success: false,
				message: "An error occurred processing your order.",
				ccNumber: "PAYPAL",
				receiptNumber: "",
				transactionId: "",
				status: "",
				redirectUrl: "",
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
				ccNumber: "PAYPAL",
				receiptNumber: "",
				transactionId: "",
				status: paymentResponse.state,
				redirectUrl: "",
				error: [
					type: "",
					message: "",
					details: []
				]
			]
		} else {
			return [
				success: true,
				message: "Payment pending",
				ccNumber: "PAYPAL",
				receiptNumber: paymentResponse.id,
				transactionId: "",
				status: paymentResponse.state,
				redirectUrl: paymentResponse.links.find {Link link->
					link.rel == "approval_url"
				}.href,
				error: [
					type: "",
					message: "",
					details: []
				]
			]
		}
	}

    public Map payWithCreditCard(Payable payableObject, String creditCardNumber, String cvv2, String expireMonth, String expireYear, String cardType) {
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
				transactionId: "",
				status: "",
				redirectUrl: "",
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
				transactionId: "",
				status: "",
				redirectUrl: "",
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
				transactionId: "",
				status: paymentResponse.state,
				redirectUrl: "",
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
				transactionId: "",
				status: paymentResponse.state,
				redirectUrl: "",
				error: [
					type: "",
					message: "",
					details: []
				]
			]
		}
    }
}
