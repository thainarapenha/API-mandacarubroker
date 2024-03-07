package com.mandacarubroker.service;

import com.mandacarubroker.domain.userActions.ResponseUserStockPortfolio;
import com.mandacarubroker.domain.userActions.UserStockPortfolio;
import com.mandacarubroker.domain.userActions.UserStockPortfolioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockPortfolioService {

    private final UserStockPortfolioRepository userStockPortfolioRepository;

    @Autowired
    public StockPortfolioService(UserStockPortfolioRepository userStockPortfolioRepository) {
        this.userStockPortfolioRepository = userStockPortfolioRepository;
    }

    public List<ResponseUserStockPortfolio> getAuthenticatedUserStockPortfolio(String userId) {
        List<UserStockPortfolio> userStockPortfolioList = userStockPortfolioRepository.findByUserId(userId);
        return userStockPortfolioList.stream()
                .map(ResponseUserStockPortfolio::fromStockPosition)
                .collect(Collectors.toList());
    }

    public UserStockPortfolio getUserStockPortfolio(String userId) {
        List<UserStockPortfolio> userStockPortfolioList = userStockPortfolioRepository.findByUserId(userId);

        if (userStockPortfolioList.isEmpty()) {
            throw new RuntimeException("Ação não foi encontrada na carteira do usuário: " + userId);
        }

        return userStockPortfolioList.get(0);
    }

    public void deposit(String userId, double amount) throws Exception {
        UserStockPortfolio userStockPortfolio = getUserStockPortfolio(userId);
        userStockPortfolio.getUser().deposit(amount);
        userStockPortfolioRepository.save(userStockPortfolio);
    }

    public void withdraw(String userId, double amount) throws Exception {
        UserStockPortfolio userStockPortfolio = getUserStockPortfolio(userId);
        userStockPortfolio.getUser().withdraw(amount);
        userStockPortfolioRepository.save(userStockPortfolio);
    }

    public double getUserPortfolioBalance(String userId) {
        List<UserStockPortfolio> userStockOwnershipList = userStockPortfolioRepository.findByUserId(userId);

        double totalBalance = userStockOwnershipList.stream()
                .mapToDouble(userStockPortfolio -> userStockPortfolio.getTotalValueCompaniesActions())
                .sum();
        return totalBalance;
    }
}
