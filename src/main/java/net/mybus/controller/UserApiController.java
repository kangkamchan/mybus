package net.mybus.controller;

import lombok.RequiredArgsConstructor;
import net.mybus.domain.User;
import net.mybus.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserApiController {
    private final UserService userService;
    @GetMapping("/{username}")
    public ResponseEntity<User> getUser(@PathVariable String username) {
        return null;
    }
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return null;
    }
    @PutMapping("/{username}")
    public ResponseEntity<User> updateUser(@PathVariable String username, @RequestBody User user) {
        return null;
    }
    @DeleteMapping("/{username}")
    public ResponseEntity<User> deleteUser(@PathVariable String username) {
        return null;
    }
}
