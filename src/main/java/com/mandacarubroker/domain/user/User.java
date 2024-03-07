package com.mandacarubroker.domain.user;

import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

import java.time.LocalDate;

@Table(name ="users")
@Entity(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private Double balance;
    private UserRole role;

    public User(RequestUserDTO requestUserDTO) {
        this.username = requestUserDTO.username();
        this.password = requestUserDTO.password();
        this.email = requestUserDTO.email();
        this.firstName = requestUserDTO.firstName();
        this.lastName = requestUserDTO.lastName();
        this.birthDate = requestUserDTO.birthDate();
        this.balance = requestUserDTO.balance();
    }
  
    public User(String username, String password, UserRole role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public void deposit(double amount) throws Exception {
        if (amount <= 0) {
            throw new Exception("O valor do depósito deve ser maior que zero.");
        }
        this.balance += amount;
    }

    public void withdraw(double amount) throws Exception {
        if (amount <= 0) {
            throw new Exception("O valor do saque deve ser maior que zero.");
        }
        if (amount > this.balance) {
            throw new Exception("Saldo insuficiente para realizar o saque.");
        }
        this.balance -= amount;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role == UserRole.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}