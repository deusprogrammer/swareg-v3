package com.swag.registration.domain.order

import com.swag.registration.domain.*
import com.swag.registration.security.*

class PayPalOrder implements Serializable, Payable {
    String  paymentType         = ""
    String  paymentId           = ""
    String  paymentStatus       = ""
    Boolean paymentCompleted    = false
    String  paymentDebugId      = ""

    String transactionId = UUID.randomUUID()

    User user
    Event event

    static hasMany = [badges: RegistrationOrderItem, addons: AddonOrderItem]

    public ArrayList<Registration> generateRegistrations() {
        ArrayList<Registration> registrations = new ArrayList<Registration>()
        badges.each { RegistrationOrderItem badge ->
            badge.quantity.times {
                registrations.add(badge.registrationLevel.generateRegistration(user, this))
            }
        }
        user.save()

        return registrations
    }

    public Double getTaxRate() {
        return event.taxRate
    }

    public Currency getCurrency() {
        return event.currency
    }

    public Double getTotal() {
        return subtotal + tax + fees
    }

    public Double getSubtotal() {
        Double total = 0.0
        badges.each { RegistrationOrderItem badge ->
            total += badge.price * badge.quantity
        }

        return total
    }

    public Double getTax() {
        return taxRate * subtotal
    }

    public Double getFees() {
        return 0.00
    }

    static constraints = {
        paymentType nullable: true
        paymentId nullable: true
        paymentStatus nullable: true
        paymentCompleted nullable: true
        paymentDebugId nullable: true
        transactionId nullable: true
    }

    @Override
    public Boolean isPaid() {
        return paymentCompleted
    }

    @Override
    public Double getPrice() {
        return total
    }

    @Override
    public User getPurchaser() {
        return user
    }

    @Override
    public String getDescription() {
        return "Badge order for ${event}"
    }}