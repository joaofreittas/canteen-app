package uninter.app.canteen.entrypoint.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import uninter.app.canteen.entrypoint.api.dto.AccountResponse;
import uninter.app.canteen.entrypoint.api.dto.CreateAccountRequest;
import uninter.app.canteen.entrypoint.api.dto.PayAccountBalanceRequest;
import uninter.app.canteen.entrypoint.api.dto.RegisterPurchaseRequest;
import uninter.app.canteen.entrypoint.api.facade.CreateAccountFacade;
import uninter.app.canteen.entrypoint.api.facade.GetAccountReportFacade;
import uninter.app.canteen.entrypoint.api.facade.GetAccountsFacade;
import uninter.app.canteen.entrypoint.api.facade.PayAccountBalanceFacade;
import uninter.app.canteen.entrypoint.api.facade.RegisterPurchaseFacade;

import java.util.List;

@RestController
@RequestMapping("/v1/account")
@RequiredArgsConstructor
public class AccountController {

    private final CreateAccountFacade createAccountFacade;
    private final RegisterPurchaseFacade registerPurchaseFacade;
    private final GetAccountReportFacade getAccountReportFacade;
    private final PayAccountBalanceFacade payAccountBalanceFacade;
    private final GetAccountsFacade getAccountsFacade;

    @GetMapping
    public List<AccountResponse> getAccounts() {
        return getAccountsFacade.process();
    }

    @GetMapping("/{accountId}")
    public AccountResponse getAccountReport(@PathVariable String accountId) {
        return getAccountReportFacade.process(accountId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AccountResponse createAccount(@RequestBody CreateAccountRequest request) {
        return createAccountFacade.process(request);
    }

    @PatchMapping("/{accountId}/register-purchase")
    public AccountResponse registerPurchase(@PathVariable String accountId, @RequestBody RegisterPurchaseRequest request) {
        return registerPurchaseFacade.process(request, accountId);
    }

    @PatchMapping("/{accountId}/pay-balance")
    public AccountResponse payAccountBalance(@PathVariable String accountId, @RequestBody PayAccountBalanceRequest request) {
        return payAccountBalanceFacade.process(request, accountId);
    }

}
