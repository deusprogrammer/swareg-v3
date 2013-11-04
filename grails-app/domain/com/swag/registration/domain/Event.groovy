package com.swag.registration.domain

import com.swag.registration.security.*
import java.util.UUID

class Event implements Serializable, Payable, Ownable {
    String uuid = UUID.randomUUID().toString()
    String apiKey = UUID.randomUUID().toString()
    String name
    String homePage = "http://"
    String merchantEmail
    Currency currency = Currency.USD
    Double taxRate = 0.0825

    Integer expectedAttendanceMin = 1000
    Integer expectedAttendanceMax = 3000
    String year = (1900 + new Date().getYear()).toString()
    Integer numeral = 1

    static hasMany = [registrations: Registration, levels: RegistrationLevel, roles: Role]
    static belongsTo = [user: User]

    public String toString() {
        if (!year.equals("0")) {
            return name + " " + year
        }
        else if (numeral != 0) {
            return name + " " + numeral
        }
        else {
            return name
        }
    }

    static constraints = {
        homePage nullable: true
        merchantEmail nullable: true
        currency nullable: true
        expectedAttendanceMin nullable: true
        expectedAttendanceMax nullable: true
        uuid nullable: true
        apiKey nullable: true
    }

    static mapping = {
        levels sort: 'price'
    }
    @Override
    public Double getPrice() {
        return 0
    }

    @Override
    public Double getTotal() {
        return 0.00
    }

    @Override
    public Double getTax() {
        return 0.00
    }

    @Override
    public Double getTaxRate() {
        return taxRate
    }

    @Override
    public User getPurchaser() {
        return user
    }

    @Override
    public Currency getCurrency() {
        return currency
    }

    @Override
    public String getDescription() {
        return name;
    }

    @Override
    public User getOwner() {
        return user
    }

    @Override
    public Boolean isPaid() {
        return true
    }
}
