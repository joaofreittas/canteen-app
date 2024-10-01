package uninter.app.canteen.core.gateway;

import uninter.app.canteen.core.domain.Account;

import java.util.List;
import java.util.Optional;

public interface AccountGateway {

    List<Account> findAll();
    Optional<Account> findById(String accountId);
    Account save(Account account);

}
