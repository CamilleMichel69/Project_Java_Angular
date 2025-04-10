package com.example.diningreviews.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.example.diningreviews.models.User;
import com.example.diningreviews.repositories.UserRepository;

@RequestMapping("/users")
@RestController
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @GetMapping
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) 
    public User createUser(@RequestBody User user) {
        if (userRepository.findByDisplayName(user.getDisplayName()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nom d'utilisateur déjà pris");
        }
        return userRepository.save(user);
    }

    @GetMapping("/{displayName}")
    public User getUser(@PathVariable String displayName) {
        return userRepository.findByDisplayName(displayName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur non trouvé"));
    }

    @PutMapping("/{displayName}")
    public User updateUser(@PathVariable String displayName, @RequestBody User user) {
        User existingUser = userRepository.findByDisplayName(displayName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur non trouvé"));

        existingUser.setCity(user.getCity());
        existingUser.setZipCode(user.getZipCode());
        existingUser.setIsPeanutAllergy(user.getIsPeanutAllergy());
        existingUser.setIsEggAllergy(user.getIsEggAllergy());
        existingUser.setIsDairyAllergy(user.getIsDairyAllergy());

        return userRepository.save(existingUser);
    }
}
