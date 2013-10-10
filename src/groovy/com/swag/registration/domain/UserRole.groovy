package com.swag.registration.domain

enum UserRole {
    NONE("None"),
    USER("SwaREG User"),
    ATENDEE("Attendee"),
    OPS("Operations Team"),
    ADMIN("Administrator")

    private String description

    public String toString() {
        return this.description
    }

    UserRole(String description) {
        this.description = description
    }
}