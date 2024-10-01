package uninter.app.canteen.entrypoint.api.facade;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uninter.app.canteen.core.domain.Account;
import uninter.app.canteen.core.usecase.CreateAccountUseCase;
import uninter.app.canteen.core.usecase.command.CreateAccountCommand;
import uninter.app.canteen.entrypoint.api.dto.AccountResponse;
import uninter.app.canteen.entrypoint.api.dto.CreateAccountRequest;
import uninter.app.canteen.entrypoint.api.dto.OwnerResponse;
import uninter.app.canteen.entrypoint.api.mapper.AccountResponseMapper;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateAccountFacadeTest {

    @InjectMocks
    private CreateAccountFacade facade;

    @Mock
    private CreateAccountUseCase useCase;

    @Mock
    private AccountResponseMapper mapper;

    @Test
    void givenCreateAccountRequest_whenProcess_thenReturnAccountResponse() {
        var request = new CreateAccountRequest("John Doe", new BigDecimal(100.0));
        var command = new CreateAccountCommand("John Doe", new BigDecimal(100.0));
        var account = new Account("John Doe", new ArrayList<>(), new BigDecimal(100.0));
        var response = new AccountResponse("1", new OwnerResponse("Jhon Doe"), new ArrayList<>(), new BigDecimal(100.0));

        when(mapper.toResponse(account)).thenReturn(response);
        when(useCase.execute(command)).thenReturn(account);

        var result = facade.process(request);

        assertEquals(result, response);
        verify(useCase).execute(command);
        verify(mapper).toResponse(account);
    }

}