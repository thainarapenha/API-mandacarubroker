package com.mandacarubroker.domain.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
    private String name;

    public User(RequestUserDTO requestUserDTO) {
        this.name = requestUserDTO.name();
    }

}