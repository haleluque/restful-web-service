package com.haleluque.rest.restful_web_services.socialMedia.service;

import com.haleluque.rest.restful_web_services.socialMedia.data.PostRepository;
import com.haleluque.rest.restful_web_services.socialMedia.data.UserRepository;
import com.haleluque.rest.restful_web_services.socialMedia.exception.UserNotFoundException;
import com.haleluque.rest.restful_web_services.socialMedia.model.Post;
import com.haleluque.rest.restful_web_services.socialMedia.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findById(int id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("id:" + id));
    }

    @Override
    public void deleteById(int id) {
        User user = findById(id);
         userRepository.delete(user);
    }

    @Override
    public List<Post> retrievePostsForUser(int id) {
        User user = findById(id);
        return user.getPosts();
    }

    @Override
    public User addPost(int id, Post post) {
        User user = findById(id);
        post.setUser(user);
        Post newPost = postRepository.save(post);
        user.getPosts().add(newPost);
        return user;
    }
}
