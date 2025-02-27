package com.haleluque.rest.restful_web_services.socialMedia.data;

import com.haleluque.rest.restful_web_services.socialMedia.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {}
