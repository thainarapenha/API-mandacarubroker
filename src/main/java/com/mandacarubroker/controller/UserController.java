package com.mandacarubroker.controller;

import com.mandacarubroker.domain.user.RequestUserDTO;
import com.mandacarubroker.domain.user.User;
import com.mandacarubroker.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    /**
     * Retorna um usuario pelo ID.
     * @return ResponseEntity contendo o objeto User se encontrado e o código de status respectivamente.
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        User user = userService.getUserById(id).orElse(null);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    /**
     * Cria um novo usuario com base nos dados fornecidos.
     * @return ResponseEntity contendo o usuario criado e código de status HTTP apropriado.
     */
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody RequestUserDTO data) {
        User createdUser = userService.createUser(data);
        return ResponseEntity.ok(createdUser);
    }

    /**
     * Atualiza um usuario existente com base no ID.
     * @return ResponseEntity contendo o objeto User atualizado e o código de status respectivamente.
     */
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody User updatedUser) {
        try {
            User result = userService.updateUser(id, updatedUser);

            if (result != null) {
                return ResponseEntity.ok(result);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Exclui um usuario com base no ID.
     * @param id ID do usuario a ser excluído.
     */@DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
    }

}
