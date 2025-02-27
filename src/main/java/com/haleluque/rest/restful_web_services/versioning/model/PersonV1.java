package com.haleluque.rest.restful_web_services.versioning.model;

public record PersonV1(String name) {

    @Override
    public String toString() {
        return "PersonV1 [name=" + name + "]";
    }

}
