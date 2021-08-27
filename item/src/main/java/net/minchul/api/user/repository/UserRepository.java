package net.minchul.api.user.repository;


import net.minchul.api.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);

    Optional<User> findByUsername(String name);

    @Query("select u from User u where u.username = :username and u.password = :password")
    User signIn(@Param("username") String username, @Param("password") String password);
}
