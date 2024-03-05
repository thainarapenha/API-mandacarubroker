package com.mandacarubroker.domain.user;

import jakarta.validation.constraints.NotBlank;

public record RequestUserDTO (
        @NotBlank(message = "User name cannot be blank")
        String name
) {
}
