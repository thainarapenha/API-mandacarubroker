package com.mandacarubroker.domain.userActions;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserStockPortfolioRepository extends JpaRepository<UserStockPortfolio, String> {
    List<UserStockPortfolio> findByUserId(String userId);
    UserStockPortfolio findByUserIdAndStockId(String userId, String stockId);
}
