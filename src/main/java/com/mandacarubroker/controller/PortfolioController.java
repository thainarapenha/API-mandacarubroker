package com.mandacarubroker.controller;

import com.mandacarubroker.domain.userActions.ResponseUserStockPortfolio;
import com.mandacarubroker.service.StockPortfolioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/portfolio")
public class PortfolioController {

    private final StockPortfolioService stockPortfolioService;

    public PortfolioController(final StockPortfolioService receivedPortfolioService) {
        this.stockPortfolioService = receivedPortfolioService;
    }

    @GetMapping
    public List<ResponseUserStockPortfolio> getAuthenticatedUserStockPortfolio() {
        return stockPortfolioService.getAuthenticatedUserStockPortfolio("userId");
    }

    @PostMapping("/deposit")
    public ResponseEntity<String> deposit(@RequestParam String userId, @RequestParam double amount) {
        try {
            stockPortfolioService.deposit(userId, amount);
            return ResponseEntity.ok("Depósito realizado com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/withdraw")
    public ResponseEntity<String> withdraw(@RequestParam String userId, @RequestParam double amount) {
        try {
            stockPortfolioService.withdraw(userId, amount);
            return ResponseEntity.ok("Saque realizado com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
