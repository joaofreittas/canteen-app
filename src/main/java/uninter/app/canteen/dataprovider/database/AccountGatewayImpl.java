package uninter.app.canteen.dataprovider.database;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uninter.app.canteen.core.domain.Account;
import uninter.app.canteen.core.gateway.AccountGateway;
import uninter.app.canteen.dataprovider.database.mapper.AccountEntityMapper;
import uninter.app.canteen.dataprovider.database.repository.AccountRepository;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AccountGatewayImpl implements AccountGateway {

    private final AccountRepository accountRepository;
    private final AccountEntityMapper mapper;

    @Override
    public Optional<Account> findById(final String accountId) {
        return accountRepository
            .findById(accountId)
            .map(mapper::toDomain);
    }

    @Override
    public Account save(final Account account) {
        var entity = mapper.toEntity(account);
        return mapper.toDomain(accountRepository.save(entity));
    }

}
