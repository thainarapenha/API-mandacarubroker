package com.mandacarubroker.domain.userActions;

import com.mandacarubroker.domain.stock.ResponseStockDTO;
import com.mandacarubroker.domain.stock.Stock;

public class ResponseUserStockPortfolio {

    private final ResponseStockDTO stock;
    private final int totalCompanies;
    private final double positionValue;

    public ResponseUserStockPortfolio(ResponseStockDTO stock, int totalCompanies, double positionValue) {
        this.stock = stock;
        this.totalCompanies = totalCompanies;
        this.positionValue = positionValue;
    }

    public static ResponseUserStockPortfolio fromStockPosition(UserStockPortfolio stockPosition) {
        ResponseStockDTO responseStockDTO = ResponseStockDTO.fromStock(stockPosition.getStock());

        return new ResponseUserStockPortfolio(
                responseStockDTO,
                stockPosition.getCompanyActions(),
                stockPosition.getTotalValueCompaniesActions()
        );
    }

    public static ResponseUserStockPortfolio fromStock(Stock stock) {
        ResponseStockDTO responseStock = ResponseStockDTO.fromStock(stock);
        return new ResponseUserStockPortfolio(
                responseStock,
                0,
                0
        );
    }

    public ResponseStockDTO getStock() {
        return stock;
    }

    public int getTotalCompanies() {
        return totalCompanies;
    }

    public double getPositionValue() {
        return positionValue;
    }
}
