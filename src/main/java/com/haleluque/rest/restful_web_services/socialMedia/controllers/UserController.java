package com.haleluque.rest.restful_web_services.socialMedia.controllers;

import com.haleluque.rest.restful_web_services.socialMedia.model.User;
import com.haleluque.rest.restful_web_services.socialMedia.data.UserDaoService;
import com.haleluque.rest.restful_web_services.socialMedia.exception.UserNotFoundException;
import jakarta.validation.Valid;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Locale;

@RestController
public class UserController {
    private final UserDaoService service;
    private final MessageSource messageSource;

    public UserController(UserDaoService service, MessageSource messageSource) {
        this.service = service;
        this.messageSource = messageSource;
    }

    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return service.findAll();
    }

    @GetMapping("/users/{id}")
    public User retrieveUser(@PathVariable int id) {
        User user = service.findOne(id);
        if (user == null)
            throw new UserNotFoundException("id:" + id);
        return user;
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User savedUser = service.save(user);

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
