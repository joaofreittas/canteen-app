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
import uninter.app.canteen.core.usecase.command.GetAccountReportCommand;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetAccountReportUseCaseTest {

    @InjectMocks
    private GetAccountReportUseCase getAccountReportUseCase;

    @Mock
    private AccountGateway accountGateway;

    @Test
    void givenGetAccountReportCommand_whenExecute_thenShouldReturnAccountReport() {
        final var command = new GetAccountReportCommand("1");
        final var accountExpected = Account.builder()
            .owner(new Owner("Joao"))
            .build();
        accountExpected.registerPurchase(new Purchase(new BigDecimal(10), LocalDateTime.now()));
        accountExpected.registerPurchase(new Purchase(new BigDecimal(10), LocalDateTime.now()));
        accountExpected.registerPurchase(new Purchase(new BigDecimal(10), LocalDateTime.now()));
        accountExpected.registerPurchase(new Purchase(new BigDecimal(10), LocalDateTime.now()));

        when(accountGateway.findById("1")).thenReturn(Optional.of(accountExpected));

        final var response = getAccountReportUseCase.execute(command);

        verify(accountGateway).findById("1");
        assertEquals(new BigDecimal(40), response.getTotalBalance());
        assertEquals("Joao", response.getOwnerName());
    }

    @Test
    void givenGetAccountReportCommand_whenAccountNotFound_thenShouldThrowException() {
        final var command = new GetAccountReportCommand("1");

        when(accountGateway.findById("1")).thenReturn(Optional.empty());

        final var exception = new NotFoundAccountException("1");
        final var thrown = assertThrows(NotFoundAccountException.class, () -> getAccountReportUseCase.execute(command));

        assertEquals(exception.getMessage(), thrown.getMessage());
    }

}
