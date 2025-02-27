package com.haleluque.rest.restful_web_services.versioning.model;

public record PersonV2(Name name) {

    @Override
    public String toString() {
        return "PersonV2 [name=" + name + "]";
    }

}