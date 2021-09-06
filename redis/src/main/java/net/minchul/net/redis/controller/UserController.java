package net.minchul.net.redis.controller;

import lombok.RequiredArgsConstructor;
import net.minchul.net.redis.domain.User;
import net.minchul.net.redis.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    public User findById(@PathVariable String id) {
        return userService.findUserById(id);
    }

    @PostMapping
    public ResponseEntity save(@RequestBody User user) {
        userService.save(user);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
