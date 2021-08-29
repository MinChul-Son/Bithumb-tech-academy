package net.minchul.api.user.controller;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import net.minchul.api.user.domain.User;
import net.minchul.api.user.domain.UserDto;
import net.minchul.api.user.service.UserServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Api(tags = "users")
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserServiceImpl userService;
    private final ModelMapper modelMapper;

    @PostMapping("/signup")
    @ApiOperation(value = "${UserController.signUp}")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "something wrong"),
            @ApiResponse(code = 403, message = "승인거절"),
            @ApiResponse(code = 422, message = "중복된 username")})
    public ResponseEntity<String> singUp(@ApiParam("SignUp User")
                                         @RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.signUp(modelMapper.map(userDto, User.class)));
    }

    @PostMapping("/signin")
    @ApiOperation(value = "${UserController.signIn}")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "something wrong"),
            @ApiResponse(code = 422, message = "유효하지 않은 아이디 / 비밀번호")})
    public ResponseEntity<UserDto> singIn(@ApiParam("SignIn User")
                                         @RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.signIn(modelMapper.map(userDto, User.class)));
    }

    @GetMapping
    public List<User> findAll() {
        return null;
    }

    @GetMapping("/{id}")
    public Optional<User> findById(@PathVariable long id) {
        return Optional.empty();
    }

    @PostMapping
    public void save(User user) {

    }

    @PutMapping
    public void update(User user) {

    }

    @GetMapping("/exists/{id}")
    public boolean existsById(@PathVariable long id) {
        return false;
    }

    @GetMapping("/count")
    public long count() {
        return 0;
    }

    @DeleteMapping
    public void deleteById(long id) {

    }
}
