package uninter.app.canteen.entrypoint.api.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uninter.app.canteen.core.usecase.GetAccountReportUseCase;
import uninter.app.canteen.core.usecase.command.GetAccountReportCommand;
import uninter.app.canteen.entrypoint.api.dto.AccountResponse;
import uninter.app.canteen.entrypoint.api.mapper.AccountResponseMapper;

@Component
@RequiredArgsConstructor
public class GetAccountReportFacade {

    private final GetAccountReportUseCase getAccountReportUseCase;
    private final AccountResponseMapper mapper;

    public AccountResponse process(String accountId) {
        final var command = new GetAccountReportCommand(accountId);
        final var account = getAccountReportUseCase.execute(command);

        return mapper.toResponse(account);
    }

}
