package com.haleluque.rest.restful_web_services.helloWorld;

public record HelloWorldBean(String message) {
    @Override
    public String toString() {
        return "HelloWorldBean [message=" + message + "]";
    }
}