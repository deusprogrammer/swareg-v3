package com.swag.registration.domain

enum Currency {
    USD("USD", "US Dollar"),
    YEN("JPY", "Japanese Yen"),
    EURO("EUR", "Euro"),
    PESO("MXN", "Mexican Peso"),
    POUND("GBP", "Pound Sterling")

    private String currencyCode
    private String description

    public String toString() {
        return "${this.description} (${this.currencyCode})"
    }

    public String getCurrencyCode() {
        return this.currencyCode
    }

    Currency(String code, String description) {
        this.currencyCode = code
        this.description = description
    }
}