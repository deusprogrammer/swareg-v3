/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.swag.registration.domain

/**
 *
 * @author Michael
 */
enum CountryState {
    TX("Texas", 0.0850)

    protected String name
    protected Double taxRate

    public String getName() {
        return name
    }

    public Double getTaxRate() {
        return taxRate
    }

    public String toString() {
        return name
    }

    public CountryState(String name, Double taxRate) {
        this.name = name
        this.taxRate = taxRate
    }
}

