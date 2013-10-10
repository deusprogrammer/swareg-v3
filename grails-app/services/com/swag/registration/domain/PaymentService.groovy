package com.swag.registration.domain

import com.paypal.api.payments.*;
import com.paypal.api.payments.util.*;
import com.paypal.core.rest.*;
import com.paypal.core.Constants;
import com.swag.registration.security.User

class PaymentService {

    public Map payWithCreditCard(Payable payableObject, String creditCardNumber, String cvv2, String expireMonth, String expireYear, String cardType) {
        User user = payableObject.getPurchaser()
        Double price = payableObject.getPrice()
        Double taxRate = payableObject.getTaxRate()
        Currency currency = payableObject.getCurrency()
        Double tax = (price * taxRate).round(2)
        Double total = (price + tax).round(2)
        String last4Digits = creditCardNumber[-4..-1]
		
		if (payableObject.isPaid()) {
			return [success: false, message: "This item is already paid for!", ccNumber: last4Digits, receiptNumber: "", status: ""]
		}
		
		println "USER:     ${user}"
		println "PRICE:    ${price}"
		println "TAXRATE:  ${taxRate}"
		println "CURRENCY: ${currency}"

        Address billingAddress = new Address()
        billingAddress.setCity(user.city)
        billingAddress.setCountryCode("US")
        billingAddress.setLine1(user.streetAddress1)
        billingAddress.setLine2(user.streetAddress2)
        billingAddress.setPostalCode(user.zipCode)
        billingAddress.setState(user.state)

        CreditCard creditCard = new CreditCard()
        creditCard.setBillingAddress(billingAddress)
        creditCard.setCvv2(cvv2)
        creditCard.setExpireMonth(expireMonth.toInteger())
        creditCard.setExpireYear(expireYear.toInteger())
        creditCard.setFirstName(user.firstName)
        creditCard.setLastName(user.lastName)
        creditCard.setNumber(creditCardNumber)
        creditCard.setType(cardType)

        Details details = new Details();
        details.setShipping("0")
        details.setSubtotal(String.format('%.2f', price))
        details.setTax(String.format('%.2f', tax))

        Amount amount = new Amount()
        amount.setCurrency(currency.currencyCode)
        amount.setTotal(String.format('%.2f', total))
        amount.setDetails(details)

        Transaction transaction = new Transaction()
        transaction.setAmount(amount)
        transaction.setDescription("${payableObject.getDescription()}")

        List<Transaction> transactions = new ArrayList<Transaction>()
        transactions.add(transaction)

        FundingInstrument fundingInstrument = new FundingInstrument()
        fundingInstrument.setCreditCard(creditCard)

        List<FundingInstrument> fundingInstrumentList = new ArrayList<FundingInstrument>()
        fundingInstrumentList.add(fundingInstrument)

        Payer payer = new Payer()
        payer.setFundingInstruments(fundingInstrumentList)
        payer.setPaymentMethod("credit_card")


        com.paypal.api.payments.Payment payment = new com.paypal.api.payments.Payment()
        payment.setIntent("sale")
        payment.setPayer(payer)
        payment.setTransactions(transactions)

        String mode = Constants.LIVE

        if (ConfigHolder.getConfig("payPal.debug") == "true") {
            mode = Constants.SANDBOX
        }

        Map<String, String> options = [mode: mode]

        try {
            String accessToken = new OAuthTokenCredential(
                ConfigHolder.getConfig("payPal.clientId"),
                ConfigHolder.getConfig("payPal.secret"),
                options)
                    .getAccessToken()

            APIContext apiContext = new APIContext(accessToken)

            com.paypal.api.payments.Payment createdPayment = payment.create(apiContext);

            if (createdPayment.getState() != "approved") {
                return [success: false, message: "Payment declined!", ccNumber: last4Digits,  receiptNumber: "", status: createdPayment.getState()]
            }

            return [success: true, message: "Payment completed successfully.", ccNumber: last4Digits, receiptNumber: createdPayment.getId(), status: createdPayment.getState()]
        } catch (PayPalRESTException e) {
            println "PaymentService failed to complete due to an exception!  EXCEPTION: ${e.message}"
            return [success: false, message: "PaymentService failed to complete due to an exception!  EXCEPTION: ${e.message}", details: e, ccNumber: last4Digits, receiptNumber: "", status: ""]
        } catch (Exception e) {
            println "PaymentService caught an unexpected exception!  EXCEPTION ${e.message}"
            return [success: false, message: "PaymentService caught an unexpected exception!  EXCEPTION ${e.message}", ccNumber: last4Digits, receiptNumber: "", status: ""]
        }
    }
}
