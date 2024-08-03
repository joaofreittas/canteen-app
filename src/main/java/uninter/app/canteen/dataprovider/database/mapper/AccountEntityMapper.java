package uninter.app.canteen.dataprovider.database.mapper;

import org.springframework.stereotype.Component;
import uninter.app.canteen.core.domain.Account;
import uninter.app.canteen.core.domain.Owner;
import uninter.app.canteen.core.domain.Purchase;
import uninter.app.canteen.dataprovider.database.entities.AccountEntity;
import uninter.app.canteen.dataprovider.database.entities.OwnerEntity;
import uninter.app.canteen.dataprovider.database.entities.PurchaseEntity;

import java.util.stream.Collectors;

@Component
public class AccountEntityMapper {

    public AccountEntity toEntity(Account account) {
        return AccountEntity.builder()
            .id(account.getId())
            .owner(new OwnerEntity(account.getOwner().name()))
            .purchases(account.getPurchases().stream()
                .map(purchase -> new PurchaseEntity(purchase.amount(), purchase.purchasedIn()))
                .collect(Collectors.toList()))
            .totalBalance(account.getTotalBalance())
            .build();
    }

    public Account toDomain(AccountEntity accountEntity) {
        return Account.builder()
            .id(accountEntity.getId())
            .owner(new Owner(accountEntity.getOwner().name()))
            .purchases(accountEntity.getPurchases().stream()
                .map(purchaseEntity -> new Purchase(purchaseEntity.amount(), purchaseEntity.purchasedIn()))
                .collect(Collectors.toList()))
            .totalBalance(accountEntity.getTotalBalance())
            .build();
    }

}
