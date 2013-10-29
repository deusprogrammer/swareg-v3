package com.swag.registration.domain

import com.swag.registration.security.User

import com.trinary.paypal.*
import com.trinary.paypal.oauth.*
import com.trinary.paypal.payment.*
import com.trinary.paypal.payment.payer.*

class PaymentService {

    public Map payWithCreditCard(Payable payableObject, String creditCardNumber, String cvv2, String expireMonth, String expireYear, String cardType) {
		PayPalConfig.setClientId(ConfigHolder.getConfig("payPal.clientId"))
		PayPalConfig.setSecret(ConfigHolder.getConfig("payPal.secret"))
		if (ConfigHolder.getConfig("payPal.debug") == "true") {
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
			return [success: false, message: "This item is already paid for!", ccNumber: last4, receiptNumber: "", status: ""]
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

		PaymentRequest payment = new PaymentRequest([
			intent: Intent.SALE,
			payer: payer
		])
		payment.addTransaction(new Transaction([
			amount: new Amount([
				currency: currency,
				details: new Details([
					subtotal: price,
					tax: tax
				])
			]),
			description: payableObject.getDescription()
		]))

		PaymentResponse paymentResponse = payment.pay()
		
		if (paymentResponse.state != "approved") {
			return [success: false, message: "Payment declined", ccNumber: last4, receiptNumber: "", status: paymentResponse.state]
		} else {
			return [success: true, message: "Payment approved!", ccNumber: last4, receiptNumber: paymentResponse.id, status: paymentResponse.state]
		}
    }
}
