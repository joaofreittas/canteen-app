package uninter.app.canteen.core.gateway;

import uninter.app.canteen.core.domain.Account;

import java.util.Optional;

public interface AccountGateway {

    Optional<Account> findById(String accountId);
    Account save(Account account);

}
