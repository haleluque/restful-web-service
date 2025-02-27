package com.haleluque.rest.restful_web_services.socialMedia.service;

import com.haleluque.rest.restful_web_services.socialMedia.model.Post;
import com.haleluque.rest.restful_web_services.socialMedia.model.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User save(User user);

    User findById(int id);

    void deleteById(int id);

    List<Post> retrievePostsForUser(int id);

    User addPost(int id, Post post);
}
