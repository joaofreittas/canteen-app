package uninter.app.canteen.entrypoint.api.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uninter.app.canteen.core.usecase.CreateAccountUseCase;
import uninter.app.canteen.core.usecase.command.CreateAccountCommand;
import uninter.app.canteen.entrypoint.api.dto.AccountResponse;
import uninter.app.canteen.entrypoint.api.dto.CreateAccountRequest;
import uninter.app.canteen.entrypoint.api.mapper.AccountResponseMapper;

@Component
@RequiredArgsConstructor
public class CreateAccountFacade {

    private final CreateAccountUseCase useCase;
    private final AccountResponseMapper mapper;

    public AccountResponse process(CreateAccountRequest request) {
        var command = new CreateAccountCommand(request.ownerName(), request.initialBalance());
        var account = useCase.execute(command);

        return mapper.toResponse(account);
    }

}
