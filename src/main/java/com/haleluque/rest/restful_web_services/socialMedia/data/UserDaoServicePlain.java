package com.haleluque.rest.restful_web_services.socialMedia.data;

import com.haleluque.rest.restful_web_services.socialMedia.model.User;
import com.haleluque.rest.restful_web_services.socialMedia.model.UserPlain;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Dao service class without JPA implementation
 */
@Component
public class UserDaoServicePlain {
    private static final List<UserPlain> users = new ArrayList<>();

    private static int usersCount = 0;

    //static block, will run once when class is loaded in memory
    static {
        users.add(new UserPlain(++usersCount, "Adam", LocalDate.now().minusYears(30)));
        users.add(new UserPlain(++usersCount, "Eve", LocalDate.now().minusYears(25)));
        users.add(new UserPlain(++usersCount, "Jim", LocalDate.now().minusYears(20)));
    }

    public List<UserPlain> findAll() {
        return users;
    }

    public UserPlain save(UserPlain user) {
        user.setId(++usersCount);
        users.add(user);
        return user;
    }

    public UserPlain findOne(int id) {
        Predicate<? super UserPlain> predicate = user -> user.getId().equals(id);
        return users.stream().filter(predicate).findFirst().orElse(null);
    }

    public void deleteById(int id) {
        Predicate<? super UserPlain> predicate = user -> user.getId().equals(id);
        users.removeIf(predicate);
    }
}
