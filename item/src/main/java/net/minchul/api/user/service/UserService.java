package net.minchul.api.user.service;

import net.minchul.api.user.domain.User;
import net.minchul.api.user.domain.UserDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface UserService {
    List<User> findAll();

    Optional<User> findById(long id);

    boolean existsById(long id);

    long count();

    void deleteById(long id);

    void save(User user);

    String signUp(User user);

    UserDto signIn(User user);
}
