package uninter.app.canteen.entrypoint.api.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uninter.app.canteen.core.usecase.GetAccountsUseCase;
import uninter.app.canteen.entrypoint.api.dto.AccountResponse;
import uninter.app.canteen.entrypoint.api.mapper.AccountResponseMapper;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GetAccountsFacade {

    private final GetAccountsUseCase getAccountsUseCase;
    private final AccountResponseMapper mapper;

    public List<AccountResponse> process() {
        return getAccountsUseCase
            .execute()
            .stream()
            .map(mapper::toResponse)
            .collect(Collectors.toList());
    }

}
