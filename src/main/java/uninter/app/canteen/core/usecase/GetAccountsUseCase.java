package uninter.app.canteen.core.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uninter.app.canteen.core.domain.Account;
import uninter.app.canteen.core.gateway.AccountGateway;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetAccountsUseCase {

    private final AccountGateway accountGateway;

    public List<Account> execute() {
        return accountGateway
            .findAll();
    }

}
