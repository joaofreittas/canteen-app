package uninter.app.canteen.core.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uninter.app.canteen.core.domain.Account;
import uninter.app.canteen.core.gateway.AccountGateway;
import uninter.app.canteen.core.usecase.command.CreateAccountCommand;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class CreateAccountUseCase {

    private final AccountGateway accountGateway;

    public Account execute(CreateAccountCommand command) {
        final var account = createAccount(command);
        return accountGateway.save(account);
    }

    private Account createAccount(final CreateAccountCommand command) {
        return new Account(command.ownerName(), new ArrayList<>(), command.initialBalance());
    }

}
