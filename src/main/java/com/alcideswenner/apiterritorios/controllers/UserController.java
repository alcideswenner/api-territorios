package com.alcideswenner.apiterritorios.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alcideswenner.apiterritorios.dto.UserDTO;
import com.alcideswenner.apiterritorios.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PreAuthorize("hasAnyAuthority('SYSTEM')")
    @PostMapping
    public void saveUser(@RequestBody UserDTO userDTO) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getAuthorities());
        userService.saveUser(userDTO);
    }
}
