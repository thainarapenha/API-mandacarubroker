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

import java.time.LocalDate;

@Table(name ="users")
@Entity(name="user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class User {
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

    public User(RequestUserDTO requestUserDTO) {
        this.username = requestUserDTO.username();
        this.password = requestUserDTO.password();
        this.email = requestUserDTO.email();
        this.firstName = requestUserDTO.firstName();
        this.lastName = requestUserDTO.lastName();
        this.birthDate = requestUserDTO.birthDate();
        this.balance = requestUserDTO.balance();
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

}