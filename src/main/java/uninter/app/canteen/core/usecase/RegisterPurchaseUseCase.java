package uninter.app.canteen.core.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uninter.app.canteen.core.common.exceptions.NotFoundAccountException;
import uninter.app.canteen.core.domain.Account;
import uninter.app.canteen.core.domain.Purchase;
import uninter.app.canteen.core.gateway.AccountGateway;
import uninter.app.canteen.core.usecase.command.RegisterPurchaseCommand;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class RegisterPurchaseUseCase {

    private final AccountGateway accountGateway;

    public Account execute(final RegisterPurchaseCommand command) {
        final var accountOptional = accountGateway.findById(command.accountId());

        if (accountOptional.isEmpty()) {
            throw new NotFoundAccountException(command.accountId());
        }

        var account = accountOptional.get();
        final var purchase = new Purchase(command.amount(), LocalDateTime.now());
        account.registerPurchase(purchase);

        return accountGateway.save(account);
    }

}
