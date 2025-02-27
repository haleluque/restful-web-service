package com.haleluque.rest.restful_web_services.socialMedia.controllers;

import com.haleluque.rest.restful_web_services.socialMedia.model.Post;
import com.haleluque.rest.restful_web_services.socialMedia.model.User;
import com.haleluque.rest.restful_web_services.socialMedia.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users/v2")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> retrieveAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/user/{id}/posts")
    public ResponseEntity<List<Post>> retrievePostsForUser(@PathVariable int id) {
        List<Post> posts = userService.retrievePostsForUser(id);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> retrieveUser(@PathVariable int id) {
        User user = userService.findById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User savedUser = userService.save(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @PostMapping("/user/{id}/posts")
    public ResponseEntity<Object> addPost(@PathVariable int id, @Valid @RequestBody Post post) {
        User user = userService.addPost(id, post);
        // Build the URI for the created resource using a different base URI
        URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/users/v2/{id}")
                .buildAndExpand(user.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id) {
        userService.deleteById(id);
    }
}
