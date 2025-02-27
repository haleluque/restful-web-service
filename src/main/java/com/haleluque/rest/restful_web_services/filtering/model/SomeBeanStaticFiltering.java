package com.haleluque.rest.restful_web_services.filtering.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@SuppressWarnings("ALL")
// specify all the property names in the class
@JsonIgnoreProperties({"field1", "field2"})
public record SomeBeanStaticFiltering(String field1, @JsonIgnore String field2, String field3) {

    @Override
    public String toString() {
        return "SomeBean [field1=" + field1 + ", field2=" + field2 + ", field3=" + field3 + "]";
    }

}
