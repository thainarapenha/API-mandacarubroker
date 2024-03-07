package com.mandacarubroker.controller;

import com.mandacarubroker.domain.user.*;
import com.mandacarubroker.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController<e> {
  @Autowired
  private AuthenticationManager authenticationManager;
  @Autowired
  private UserRepository repository;
  @Autowired
  private TokenService tokenService;

  @PostMapping("/login")
  public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
    try {
      var usernamePassword = new UsernamePasswordAuthenticationToken(data.username(), data.password());
      var auth = this.authenticationManager.authenticate(usernamePassword);

      var token = tokenService.generateToken((User) auth.getPrincipal());
      var msg = "Sucess";
      return ResponseEntity.ok(new LoginResponseDTO(token, msg));

    } catch (AuthenticationException e) {
      // Manejar a exceção de autenticação e retornar um erro
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
              new LoginResponseDTO("", "Login fail: Authentication error"));
    }

  }

  @PostMapping("/register")
  public ResponseEntity register(@RequestBody @Valid RegisterDTO data){
    if(this.repository.findByUsername(data.username()) != null) return ResponseEntity.badRequest().build();

    String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
    User newUser = new User(data.username(), encryptedPassword, data.role());

    this.repository.save(newUser);

    return ResponseEntity.ok().build();
  }
}
