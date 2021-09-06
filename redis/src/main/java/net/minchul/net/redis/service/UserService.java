package net.minchul.net.redis.service;

import lombok.RequiredArgsConstructor;
import net.minchul.net.redis.converter.BytesToUserConverter;
import net.minchul.net.redis.converter.UserToByteConverter;
import net.minchul.net.redis.domain.User;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final RedisTemplate redisTemplate;
    private final UserToByteConverter userToByteConverter;
    private final BytesToUserConverter bytesToUserConverter;

    public void save(User user) {
        HashOperations operations = redisTemplate.opsForHash();
        operations.put("user", user.getId(), userToByteConverter.convert(user));
    }

    public User findUserById(String id) {
        HashOperations operations = redisTemplate.opsForHash();
        return bytesToUserConverter.convert((byte[]) operations.get("user", id));
    }
}
