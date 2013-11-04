package com.swag.registration.domain

enum Currency {
    USD("USD", "US Dollar", "&#36"),
    YEN("JPY", "Japanese Yen", "&#165"),
    EURO("EUR", "Euro", "&#8364"),
    PESO("MXN", "Mexican Peso", "&#8369"),
    POUND("GBP", "Pound Sterling", "&#163")

    private String currencyCode
    private String description
    private String escapeCode

    public String toString() {
        return "${this.description} (${this.currencyCode})"
    }

    public String getCurrencyCode() {
        return this.currencyCode
    }

    public String getEscapeCode() {
        return this.escapeCode
    }

    Currency(String code, String description, String escapeCode) {
        this.currencyCode = code
        this.description = description
        this.escapeCode = escapeCode
    }
}