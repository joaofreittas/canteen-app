package uninter.app.canteen.dataprovider.database;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uninter.app.canteen.core.domain.Account;
import uninter.app.canteen.core.gateway.AccountGateway;
import uninter.app.canteen.dataprovider.database.mapper.AccountEntityMapper;
import uninter.app.canteen.dataprovider.database.repository.AccountRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AccountGatewayImpl implements AccountGateway {

    private final AccountRepository accountRepository;
    private final AccountEntityMapper mapper;

    @Override
    public List<Account> findAll() {
        return accountRepository
            .findAll()
            .stream()
            .map(mapper::toDomain)
            .collect(Collectors.toList());
    }

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
