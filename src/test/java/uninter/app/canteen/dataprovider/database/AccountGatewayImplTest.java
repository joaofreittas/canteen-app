package uninter.app.canteen.dataprovider.database;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uninter.app.canteen.core.domain.Account;
import uninter.app.canteen.core.domain.Owner;
import uninter.app.canteen.dataprovider.database.entities.AccountEntity;
import uninter.app.canteen.dataprovider.database.entities.OwnerEntity;
import uninter.app.canteen.dataprovider.database.mapper.AccountEntityMapper;
import uninter.app.canteen.dataprovider.database.repository.AccountRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountGatewayImplTest {

    private AccountGatewayImpl accountGateway;

    @Mock
    private AccountRepository accountRepository;

    private AccountEntityMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new AccountEntityMapper();
        accountGateway = new AccountGatewayImpl(accountRepository, mapper);
    }

    @Test
    void givenAccountId_whenExecuteFindById_thenReturnAccount() {
        var accountEntity = new AccountEntity("1", new OwnerEntity("Jhon"), new ArrayList<>(), new BigDecimal(100));
        var account = mapper.toDomain(accountEntity);

        when(accountRepository.findById("1")).thenReturn(Optional.of(accountEntity));
        var result = accountGateway.findById("1");

        assertTrue(result.isPresent());
        assertEquals(account.getId(), result.get().getId());
        assertEquals(account.getOwner().name(), result.get().getOwner().name());
        assertEquals(account.getTotalBalance(), result.get().getTotalBalance());
    }

    @Test
    void givenAccountId_whenExecuteFindById_andNotFoundAccount_thenReturnEmpty() {
        when(accountRepository.findById("1")).thenReturn(Optional.empty());
        var result = accountGateway.findById("1");

        assertTrue(result.isEmpty());
    }

    @Test
    void givenAccount_whenExecuteSave_thenReturnAccount() {
        var account = Account.builder()
            .id("1")
            .owner(new Owner("Jhon"))
            .purchases(new ArrayList<>())
            .totalBalance(new BigDecimal(100))
            .build();
        var accountEntity = mapper.toEntity(account);

        when(accountRepository.save(any(AccountEntity.class))).thenReturn(accountEntity);
        var result = accountGateway.save(account);

        assertEquals(account.getId(), result.getId());
        assertEquals(account.getOwner().name(), result.getOwner().name());
        assertEquals(account.getTotalBalance(), result.getTotalBalance());
    }

}