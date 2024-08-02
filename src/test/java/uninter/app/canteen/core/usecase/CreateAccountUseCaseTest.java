package uninter.app.canteen.core.usecase;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uninter.app.canteen.core.domain.Account;
import uninter.app.canteen.core.domain.Owner;
import uninter.app.canteen.core.gateway.AccountGateway;
import uninter.app.canteen.core.usecase.command.CreateAccountCommand;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateAccountUseCaseTest {

    @InjectMocks
    private CreateAccountUseCase createAccountUseCase;

    @Mock
    private AccountGateway accountGateway;

    @Test
    void givenCreateAccountCommand_whenExecute_thenReturnAccount() {
        final var command = new CreateAccountCommand("John Doe", BigDecimal.TEN);
        final var expectedAccount = new Account("1", new Owner("John Doe"), new ArrayList<>(), BigDecimal.TEN);

        when(accountGateway.save(any(Account.class))).thenReturn(expectedAccount);
        final var account = createAccountUseCase.execute(command);

        assertNotNull(account);
        assertEquals("John Doe", account.getOwnerName());
        assertEquals(BigDecimal.TEN, account.getTotalBalance());
    }

}