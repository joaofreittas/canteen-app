package uninter.app.canteen.core.usecase;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uninter.app.canteen.core.common.exceptions.InvalidAmountForPayBalanceException;
import uninter.app.canteen.core.common.exceptions.NotFoundAccountException;
import uninter.app.canteen.core.domain.Account;
import uninter.app.canteen.core.domain.Owner;
import uninter.app.canteen.core.domain.Purchase;
import uninter.app.canteen.core.gateway.AccountGateway;
import uninter.app.canteen.core.usecase.command.PayBalanceCommand;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PayBalanceUseCaseTest {

    @InjectMocks
    private PayAccountBalanceUseCase payAccountBalanceUseCase;

    @Mock
    private AccountGateway accountGateway;

    @Test
    void givenPayBalanceCommand_whenExecute_thenShouldSubtractTotalBalanceWithAmountPayed() {
        final var command = new PayBalanceCommand("1", new BigDecimal(5));
        final var accountExpected = Account.builder()
            .owner(new Owner("Joao"))
            .build();
        accountExpected.registerPurchase(new Purchase(new BigDecimal(10), LocalDateTime.now()));
        accountExpected.registerPurchase(new Purchase(new BigDecimal(10), LocalDateTime.now()));
        accountExpected.registerPurchase(new Purchase(new BigDecimal(10), LocalDateTime.now()));
        accountExpected.registerPurchase(new Purchase(new BigDecimal(10), LocalDateTime.now()));

        when(accountGateway.findById("1")).thenReturn(Optional.of(accountExpected));

        final var response = payAccountBalanceUseCase.execute(command);

        verify(accountGateway).findById("1");
        assertEquals(new BigDecimal(35), response.getTotalBalance());
        assertEquals("Joao", response.getOwnerName());
    }

    @Test
    void givenPayBalanceCommand_whenAccountNotFound_thenShouldThrowException() {
        final var command = new PayBalanceCommand("1", new BigDecimal(5));

        when(accountGateway.findById("1")).thenReturn(Optional.empty());

        assertThrows(NotFoundAccountException.class, () -> payAccountBalanceUseCase.execute(command));
    }

    @Test
    void givenPayBalanceCommand_whenAmountIsZero_thenShouldThrowException() {
        final var command = new PayBalanceCommand("1", new BigDecimal(0));
        final var accountExpected = Account.builder()
            .owner(new Owner("Joao"))
            .build();
        accountExpected.registerPurchase(new Purchase(new BigDecimal(10), LocalDateTime.now()));
        accountExpected.registerPurchase(new Purchase(new BigDecimal(10), LocalDateTime.now()));
        accountExpected.registerPurchase(new Purchase(new BigDecimal(10), LocalDateTime.now()));
        accountExpected.registerPurchase(new Purchase(new BigDecimal(10), LocalDateTime.now()));

        when(accountGateway.findById("1")).thenReturn(Optional.of(accountExpected));

        assertThrows(InvalidAmountForPayBalanceException.class, () -> payAccountBalanceUseCase.execute(command));
    }

    @Test
    void givenPayBalanceCommand_whenAmountIsNull_thenShouldThrowException() {
        final var command = new PayBalanceCommand("1", null);
        final var accountExpected = Account.builder()
            .owner(new Owner("Joao"))
            .build();
        accountExpected.registerPurchase(new Purchase(new BigDecimal(10), LocalDateTime.now()));
        accountExpected.registerPurchase(new Purchase(new BigDecimal(10), LocalDateTime.now()));
        accountExpected.registerPurchase(new Purchase(new BigDecimal(10), LocalDateTime.now()));
        accountExpected.registerPurchase(new Purchase(new BigDecimal(10), LocalDateTime.now()));

        when(accountGateway.findById("1")).thenReturn(Optional.of(accountExpected));

        assertThrows(InvalidAmountForPayBalanceException.class, () -> payAccountBalanceUseCase.execute(command));
    }

}
