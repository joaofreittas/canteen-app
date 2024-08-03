package uninter.app.canteen.entrypoint.api.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uninter.app.canteen.core.usecase.PayAccountBalanceUseCase;
import uninter.app.canteen.core.usecase.command.PayBalanceCommand;
import uninter.app.canteen.entrypoint.api.dto.AccountResponse;
import uninter.app.canteen.entrypoint.api.dto.PayAccountBalanceRequest;
import uninter.app.canteen.entrypoint.api.mapper.AccountResponseMapper;

@Component
@RequiredArgsConstructor
public class PayAccountBalanceFacade {

    private final PayAccountBalanceUseCase payAccountBalanceUseCase;
    private final AccountResponseMapper accountResponseMapper;

    public AccountResponse process(final PayAccountBalanceRequest request, final String accountId) {
        final var command = new PayBalanceCommand(accountId, request.amount());
        final var account = payAccountBalanceUseCase.execute(command);

        return accountResponseMapper.toResponse(account);
    }

}
