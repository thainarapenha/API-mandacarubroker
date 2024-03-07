package com.mandacarubroker.domain.userActions;

import com.mandacarubroker.domain.stock.Stock;
import com.mandacarubroker.domain.user.User;

import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Table(name = "user_actions")
@Entity(name = "user_actions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class UserStockPortfolio {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private int companyActions;
    @OneToOne
    private Stock stock;
    @OneToOne
    private User user;


    public UserStockPortfolio(final RequestUserStockPortfolioDTO requestUserStockPortfolioDTO, final Stock paramsStockReceved, final User paramsUserReceved) {
        this.companyActions = requestUserStockPortfolioDTO.companiesActions();
        this.stock = paramsStockReceved;
        this.user = paramsUserReceved;
    }


    public double getTotalValueCompaniesActions() {
        return companyActions * stock.getPrice();
    }

    public Stock getCompanies() {
        return stock;
    }


}
