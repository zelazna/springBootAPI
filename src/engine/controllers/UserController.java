package engine.controllers;

import engine.models.User;
import engine.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/register", consumes = "application/json")
    public ResponseEntity<?> createUser(@Valid @RequestBody User user) {
        System.out.println(user.getEmail());
        System.out.println(user.getPassword());
        userService.save(user);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
