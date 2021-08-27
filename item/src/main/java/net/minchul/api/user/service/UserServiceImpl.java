package net.minchul.api.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.minchul.api.security.domain.SecurityProvider;
import net.minchul.api.security.exception.SecurityRuntimeException;
import net.minchul.api.user.domain.Role;
import net.minchul.api.user.domain.User;
import net.minchul.api.user.domain.UserDto;
import net.minchul.api.user.repository.UserRepository;
import net.minchul.api.util.Proxy;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final SecurityProvider provider;
    private final AuthenticationManager manager;
    private final ModelMapper modelMapper;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public boolean existsById(long id) {
        return userRepository.existsById(id);
    }

    @Override
    public long count() {
        return userRepository.count();
    }

    @Override
    public void deleteById(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public String signUp(User user) {
        if (!userRepository.existsByUsername(user.getUsername())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            List<Role> list = new ArrayList<>();
            list.add(Role.USER);
            user.setRoles(list);
            userRepository.save(user);
            log.info("user={}", user);
            return provider.createToken(user.getUsername(), user.getRoles());
        }
        throw new SecurityRuntimeException("중복된 ID입니다.", HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @Override
    public UserDto signIn(User user) {
        try {
            UserDto userDto = modelMapper.map(user, UserDto.class);
            String token = (passwordEncoder.matches(
                    user.getPassword(),
                    userRepository.findByUsername(user.getUsername()).get().getPassword()))
                    ? provider.createToken(user.getUsername(), userRepository.findByUsername(user.getUsername()).get().getRoles())
                    : "잘못된 패스워드입니다.";
            userDto.setToken(token);
            return userDto;
        } catch (Exception e) {
            throw new SecurityRuntimeException("유효하지 않은 아이디 / 비밀번호", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

}
