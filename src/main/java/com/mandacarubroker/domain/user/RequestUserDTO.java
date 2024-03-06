package com.mandacarubroker.domain.user;

import jakarta.validation.constraints.*;
import org.checkerframework.common.value.qual.DoubleVal;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record RequestUserDTO (
        @Email(message = "The email format is invalid")
        String email,
        @NotBlank(message = "Username cannot be blank")
        String username,
        @Size(min = 6, message = "Password must be at least 6 characters long")
        String password,
        @NotBlank(message = "First name cannot be blank")
        String firstName,
        @NotBlank(message = "Last name cannot be blank")
        String lastName,

        @Past(message = "The date must be in the past")
        LocalDate birthDate,

        @PositiveOrZero
        Double balance
) {
}
