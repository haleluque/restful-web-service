package com.haleluque.rest.restful_web_services.socialMedia.data;

import com.haleluque.rest.restful_web_services.socialMedia.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {

}