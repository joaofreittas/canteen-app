package uninter.app.canteen.entrypoint.api.mapper;

import org.springframework.stereotype.Component;
import uninter.app.canteen.core.domain.Account;
import uninter.app.canteen.core.domain.Purchase;
import uninter.app.canteen.entrypoint.api.dto.AccountResponse;
import uninter.app.canteen.entrypoint.api.dto.OwnerResponse;
import uninter.app.canteen.entrypoint.api.dto.PurchaseResponse;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AccountResponseMapper {

    public AccountResponse toResponse(Account account) {
        return AccountResponse.builder()
            .id(account.getId())
            .owner(new OwnerResponse(account.getOwner().name()))
            .purchases(toResponse(account.getPurchases()))
            .totalBalance(account.getTotalBalance())
            .build();
    }

    private List<PurchaseResponse> toResponse(List<Purchase> purchases) {
        return purchases.stream()
            .map(purchase -> new PurchaseResponse(purchase.amount(), purchase.purchasedIn()))
            .collect(Collectors.toList());
    }

}
