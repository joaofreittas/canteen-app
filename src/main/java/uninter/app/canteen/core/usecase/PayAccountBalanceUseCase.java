package uninter.app.canteen.core.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uninter.app.canteen.core.common.exceptions.NotFoundAccountException;
import uninter.app.canteen.core.domain.Account;
import uninter.app.canteen.core.gateway.AccountGateway;
import uninter.app.canteen.core.usecase.command.PayBalanceCommand;

@Component
@RequiredArgsConstructor
public class PayAccountBalanceUseCase {

    private final AccountGateway accountGateway;

    public Account execute(final PayBalanceCommand command) {
        final var accountOptional = accountGateway.findById(command.accountId());

        if (accountOptional.isEmpty()) {
            throw new NotFoundAccountException(command.accountId());
        }

        var account = accountOptional.get();
        account.payBalance(command.amount());
        accountGateway.save(account);

        return account;
    }

}
