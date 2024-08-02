package uninter.app.canteen.core.usecase;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uninter.app.canteen.core.common.exceptions.NotFoundAccountException;
import uninter.app.canteen.core.domain.Account;
import uninter.app.canteen.core.domain.Owner;
import uninter.app.canteen.core.domain.Purchase;
import uninter.app.canteen.core.gateway.AccountGateway;
import uninter.app.canteen.core.usecase.command.RegisterPurchaseCommand;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RegisterPurchaseUseCaseTest {

    @InjectMocks
    private RegisterPurchaseUseCase registerPurchaseUseCase;

    @Mock
    private AccountGateway accountGateway;

    @Test
    void givenRegisterPurchaseCommand_whenExecuteUseCase_shouldThenRegisterPurchase() {
        final var command = RegisterPurchaseCommand.builder()
            .accountId("1")
            .amount(new BigDecimal(10.0))
            .build();
        final var owner = new Owner("Joao");
        final var account = Account.builder()
            .owner(owner)
            .build();

        final var purchases = List.of(new Purchase(command.amount(), LocalDateTime.now()));
        final var accountExpected = Account.builder()
            .purchases(purchases)
            .owner(owner)
            .build();

        when(accountGateway.findById("1")).thenReturn(Optional.of(account));
        when(accountGateway.save(any(Account.class))).thenReturn(accountExpected);

        var response = registerPurchaseUseCase.execute(command);

        assertEquals(accountExpected.getTotalBalance(), response.getTotalBalance());
        assertEquals(accountExpected.getOwnerName(), response.getOwnerName());
    }

    @Test
    void givenRegisterPurchaseCommand_whenExecute_andAccountIdNotFound_shouldThenThrowException() {
        final var command = RegisterPurchaseCommand.builder()
            .accountId("1")
            .amount(new BigDecimal(10.0))
            .build();

        when(accountGateway.findById("1")).thenReturn(Optional.empty());

        assertThrows(NotFoundAccountException.class, () -> registerPurchaseUseCase.execute(command));
    }

}
