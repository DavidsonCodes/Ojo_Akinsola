package com.BankApplication.controller;

import com.BankApplication.model.AccountUser;
import com.BankApplication.service.AccountUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class AccountUserController {

    @Autowired
    private AccountUserService userService;

    @GetMapping ("/allUsers")
    public ResponseEntity<List<AccountUser>> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountUser> getUserById(@PathVariable(value = "id") int userId) {
        AccountUser user = userService.getUserById(userId).getBody();
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<AccountUser> getUserByUsername(@PathVariable(value = "username") String username) {
        AccountUser user = userService.getUserByUsername(username);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(user);
    }

    @PostMapping ("/single")
    public ResponseEntity<AccountUser> createUser(@Valid @RequestBody AccountUser user) {
        AccountUser newUser = userService.createAccount(user).getBody();
        return ResponseEntity.ok().body(newUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Optional<AccountUser>> updateUser(@PathVariable(value = "id") int userId,
                                                            @Valid @RequestBody AccountUser userDetails) {
        Optional<AccountUser> updatedUser = userService.updateUser(userId, userDetails);
        if (updatedUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable(value = "id") int userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }
}