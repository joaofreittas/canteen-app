package uninter.app.canteen.core.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import uninter.app.canteen.core.common.exceptions.InvalidAmountForPayBalanceException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

@Builder
@Getter
@AllArgsConstructor
public class Account {

    private String id;
    private Owner owner;
    private List<Purchase> purchases;
    private BigDecimal totalBalance;

    public Account(String ownerName, List<Purchase> purchases, BigDecimal initialBalance) {
        this.owner = new Owner(ownerName);
        this.purchases = purchases;
        this.totalBalance = initialBalance;
    }

    public void registerPurchase(Purchase purchase) {
        if (isNull(purchases)) {
            purchases = new ArrayList<>();
        }

        if (isNull(totalBalance)) {
            totalBalance = BigDecimal.ZERO;
        }

        purchases.add(purchase);
        totalBalance = totalBalance.add(purchase.amount());
    }

    public void payBalance(BigDecimal amount) {
        if (isNull(amount) || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidAmountForPayBalanceException();
        }

        totalBalance = totalBalance.subtract(amount);
    }

    public String getOwnerName() {
        return owner.name();
    }

}
