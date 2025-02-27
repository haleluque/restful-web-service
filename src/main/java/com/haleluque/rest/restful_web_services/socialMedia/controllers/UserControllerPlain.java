package com.haleluque.rest.restful_web_services.socialMedia.controllers;

import com.haleluque.rest.restful_web_services.socialMedia.model.User;
import com.haleluque.rest.restful_web_services.socialMedia.data.UserDaoServicePlain;
import com.haleluque.rest.restful_web_services.socialMedia.exception.UserNotFoundException;
import com.haleluque.rest.restful_web_services.socialMedia.model.UserPlain;
import jakarta.validation.Valid;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Locale;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserControllerPlain {
    private final UserDaoServicePlain service;
    private final MessageSource messageSource;

    public UserControllerPlain(UserDaoServicePlain service, MessageSource messageSource) {
        this.service = service;
        this.messageSource = messageSource;
    }

    @GetMapping("/users")
    public List<UserPlain> retrieveAllUsers() {
        return service.findAll();
    }

    /**
     * HATEOAS example
     *
     * @param id user id
     * @return User and link to the users page
     */
    @GetMapping("/users/{id}")
    public EntityModel<UserPlain> retrieveUser(@PathVariable int id) {
        UserPlain user = service.findOne(id);
        if (user == null)
            throw new UserNotFoundException("id:" + id);

        EntityModel<UserPlain> userEntityModel = EntityModel.of(user);
        //create a link to a specific method in the controller
        WebMvcLinkBuilder linkBuilder = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        userEntityModel.add(linkBuilder.withRel("all-users"));
        return userEntityModel;
    }

    @PostMapping("/users")
    public ResponseEntity<UserPlain> createUser(@Valid @RequestBody UserPlain user) {
        UserPlain savedUser = service.save(user);

        //automatic redirects to the url of get the new user by id
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        service.deleteById(id);
    }

    @GetMapping(path = "/hello-world-internationalized")
    public String helloWorldInternationalized() {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage("good.morning.message", null, "Default Message", locale );
    }
}
