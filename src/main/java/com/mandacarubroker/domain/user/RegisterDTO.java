package com.mandacarubroker.domain.user;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record RegisterDTO(@Email(message = "The email format is invalid")
                          String email,
                          @NotBlank(message = "Username cannot be blank")
                          String username,

                          String password,
                          @NotBlank(message = "First name cannot be blank")
                          @NotNull(message = "First name cannot be null")
                          String first_name,
                          @NotBlank(message = "Last name cannot be blank")
                          @NotNull(message = "Last name cannot be null")
                          String last_name,
                          @NotNull(message = "role cannot be null")
                          UserRole role,

                          @Past(message = "The date must be in the past")
                          LocalDate birth_date,

                          @PositiveOrZero
                          Double balance) {
}




       /* String username, String password, String email,
        String firstName, String lastName, UserRole role, LocalDate birthDate, Double balance*/