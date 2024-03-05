package com.mandacarubroker.controller;

import com.mandacarubroker.domain.user.User;
import com.mandacarubroker.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Retorna a lista de todos os usuarios.
     * @return Lista de objetos Users.
     */
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}
