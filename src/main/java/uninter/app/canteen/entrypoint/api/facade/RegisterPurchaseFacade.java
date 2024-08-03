package uninter.app.canteen.entrypoint.api.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uninter.app.canteen.core.usecase.RegisterPurchaseUseCase;
import uninter.app.canteen.core.usecase.command.RegisterPurchaseCommand;
import uninter.app.canteen.entrypoint.api.dto.AccountResponse;
import uninter.app.canteen.entrypoint.api.dto.RegisterPurchaseRequest;
import uninter.app.canteen.entrypoint.api.mapper.AccountResponseMapper;

@Component
@RequiredArgsConstructor
public class RegisterPurchaseFacade {

    private final RegisterPurchaseUseCase registerPurchaseUseCase;
    private final AccountResponseMapper mapper;

    public AccountResponse process(final RegisterPurchaseRequest request, final String accountId) {
        final var command = new RegisterPurchaseCommand(accountId, request.amount());
        final var account = registerPurchaseUseCase.execute(command);

        return mapper.toResponse(account);
    }

}
