package com.alcideswenner.apiterritorios.controllers;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alcideswenner.apiterritorios.dto.UserDTO;
import com.alcideswenner.apiterritorios.entities.User;
import com.alcideswenner.apiterritorios.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PreAuthorize("hasAnyAuthority('SYSTEM')")
    @PostMapping
    public ResponseEntity<?> saveUser(@RequestBody User user) {
        Optional<User> opt = userService.saveUser(user);
        return opt.isPresent()
                ? ResponseEntity.ok().body(new UserDTO(opt.get()))
                : ResponseEntity.badRequest().build();
    }

    @PreAuthorize("hasAnyAuthority('SYSTEM','ADMIN')")
    @GetMapping
    public ResponseEntity<?> findAll() {
        return userService.findAll().isPresent()
                ? ResponseEntity.ok().body(userService.findAll().get())
                : ResponseEntity.notFound().build();
    }

    @PreAuthorize("hasAnyAuthority('SYSTEM')")
    @DeleteMapping(value = "/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
    }
}
