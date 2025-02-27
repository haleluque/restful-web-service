package com.haleluque.rest.restful_web_services.versioning.model;

public record Name(String firstName, String lastName) {

    @Override
    public String toString() {
        return "Name [firstName=" + firstName + ", lastName=" + lastName + "]";
    }

}