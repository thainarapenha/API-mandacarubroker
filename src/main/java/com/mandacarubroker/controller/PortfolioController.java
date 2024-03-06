package com.mandacarubroker.controller;

import com.mandacarubroker.domain.userActions.ResponseUserStockPortfolio;
import com.mandacarubroker.service.StockPortfolioService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
