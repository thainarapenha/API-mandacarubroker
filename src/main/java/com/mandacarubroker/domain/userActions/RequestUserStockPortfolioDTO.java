package com.mandacarubroker.domain.userActions;

import jakarta.validation.constraints.Positive;

public record RequestUserStockPortfolioDTO(
        @Positive(message = "O valor da ação deve ser maior que zero")
        int companiesActions
) {
}


