package com.haleluque.rest.restful_web_services.filtering.controller;

import java.util.Arrays;
import java.util.List;

import com.haleluque.rest.restful_web_services.filtering.model.SomeBean;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {

    @GetMapping("/filtering") //field2
    public MappingJacksonValue filtering() {
        SomeBean someBean = new SomeBean("value1", "value2", "value3");
        return defineCustomFilter(new MappingJacksonValue(someBean), "field1","field3");
    }

    @GetMapping("/filtering-list")
    public MappingJacksonValue filteringList() {
        List<SomeBean> list = Arrays.asList(new SomeBean("value1", "value2", "value3"),
                new SomeBean("value4", "value5", "value6"));
        //Define a custom filter with different values
        return defineCustomFilter(new MappingJacksonValue(list), "field2", "field3");
    }

    private static MappingJacksonValue defineCustomFilter(MappingJacksonValue bean, String... fields) {
        //Define a custom filter
        SimpleBeanPropertyFilter filter =
                SimpleBeanPropertyFilter.filterOutAllExcept(fields);
        //pass the custom filter name that must be defined in the bean
        FilterProvider filters =
                new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
        bean.setFilters(filters);
        return bean;
    }

}
