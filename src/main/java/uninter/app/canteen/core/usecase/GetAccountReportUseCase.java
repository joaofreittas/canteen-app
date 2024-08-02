package uninter.app.canteen.core.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uninter.app.canteen.core.common.exceptions.NotFoundAccountException;
import uninter.app.canteen.core.domain.Account;
import uninter.app.canteen.core.gateway.AccountGateway;
import uninter.app.canteen.core.usecase.command.GetAccountReportCommand;

@Component
@RequiredArgsConstructor
public class GetAccountReportUseCase {

    private final AccountGateway accountGateway;

    public Account execute(GetAccountReportCommand command) {
        return accountGateway
            .findById(command.accountId())
            .orElseThrow(() -> new NotFoundAccountException(command.accountId()));
    }

}
