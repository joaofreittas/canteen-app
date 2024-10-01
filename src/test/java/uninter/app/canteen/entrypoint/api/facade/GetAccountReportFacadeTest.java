package uninter.app.canteen.entrypoint.api.facade;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uninter.app.canteen.core.domain.Account;
import uninter.app.canteen.core.usecase.GetAccountReportUseCase;
import uninter.app.canteen.core.usecase.command.GetAccountReportCommand;
import uninter.app.canteen.entrypoint.api.dto.AccountResponse;
import uninter.app.canteen.entrypoint.api.dto.OwnerResponse;
import uninter.app.canteen.entrypoint.api.mapper.AccountResponseMapper;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetAccountReportFacadeTest {

    @InjectMocks
    private GetAccountReportFacade facade;

    @Mock
    private GetAccountReportUseCase useCase;
    @Mock
    private AccountResponseMapper mapper;

    @Test
    void givenAccountId_whenProcess_thenReturnAccountResponse() {
        var accountId = "1";
        final var command = new GetAccountReportCommand(accountId);
        var account = new Account("John Doe", new ArrayList<>(), new BigDecimal(100.0));
        var response = new AccountResponse(accountId, new OwnerResponse("Jhon Doe"), new ArrayList<>(), new BigDecimal(100.0));

        when(mapper.toResponse(account)).thenReturn(response);
        when(useCase.execute(command)).thenReturn(account);

        var result = facade.process(accountId);

        assertEquals(result, response);
        verify(useCase).execute(command);
        verify(mapper).toResponse(account);
    }

}