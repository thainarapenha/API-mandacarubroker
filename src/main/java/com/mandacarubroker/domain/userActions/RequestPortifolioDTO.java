package com.mandacarubroker.domain.userActions;

import jakarta.validation.constraints.NotNull;

public record RequestPortifolioDTO(
        @NotNull(message = "O valor da ação deve ser maior que zero")
        String userId,
        @NotNull(message = "O valor da ação deve ser maior que zero")
        Double amount) {

}
