package uninter.app.canteen.entrypoint.api.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uninter.app.canteen.entrypoint.api.dto.AccountResponse;
import uninter.app.canteen.entrypoint.api.dto.CreateAccountRequest;
import uninter.app.canteen.entrypoint.api.dto.OwnerResponse;
import uninter.app.canteen.entrypoint.api.dto.PayAccountBalanceRequest;
import uninter.app.canteen.entrypoint.api.dto.RegisterPurchaseRequest;
import uninter.app.canteen.entrypoint.api.facade.CreateAccountFacade;
import uninter.app.canteen.entrypoint.api.facade.GetAccountReportFacade;
import uninter.app.canteen.entrypoint.api.facade.PayAccountBalanceFacade;
import uninter.app.canteen.entrypoint.api.facade.RegisterPurchaseFacade;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountControllerTest {

    @InjectMocks
    private AccountController accountController;

    @Mock
    private CreateAccountFacade createAccountFacade;

    @Mock
    private RegisterPurchaseFacade registerPurchaseFacade;

    @Mock
    private GetAccountReportFacade getAccountReportFacade;

    @Mock
    private PayAccountBalanceFacade payAccountBalanceFacade;

    @Test
    void givenAccountId_whenGetAccountReport_shouldCallFacadeAndReturnAccount() {
        var accountId = "1";
        var owner = new OwnerResponse("John Doe");
        var account = new AccountResponse(accountId, owner, null, BigDecimal.ZERO);

        when(getAccountReportFacade.process(account.id())).thenReturn(account);

        var result = accountController.getAccountReport(accountId);

        assertEquals(account, result);
        verify(getAccountReportFacade).process(accountId);
    }

    @Test
    void givenCreateAccountRequest_whenCreateAccount_shouldCallFacadeAndReturnAccount() {
        var request = new CreateAccountRequest("John Doe", new BigDecimal(10));
        var owner = new OwnerResponse(request.ownerName());
        var account = new AccountResponse("1", owner, null, BigDecimal.ZERO);

        when(createAccountFacade.process(request)).thenReturn(account);

        var result = accountController.createAccount(request);

        assertEquals(account, result);
        verify(createAccountFacade).process(request);
    }

    @Test
    void givenAccountIdAndRegisterPurchaseRequest_whenRegisterPurchase_shouldCallFacadeAndReturnAccount() {
        var accountId = "1";
        var request = new RegisterPurchaseRequest(new BigDecimal(10));
        var owner = new OwnerResponse("John Doe");
        var account = new AccountResponse(accountId, owner, null, BigDecimal.ZERO);

        when(registerPurchaseFacade.process(request, accountId)).thenReturn(account);

        var result = accountController.registerPurchase(accountId, request);

        assertEquals(account, result);
        verify(registerPurchaseFacade).process(request, accountId);
    }

    @Test
    void givenAccountIdAndPayAccountBalanceRequest_whenPayAccountBalance_shouldCallFacadeAndReturnAccount() {
        var accountId = "1";
        var request = new PayAccountBalanceRequest(new BigDecimal(10));
        var owner = new OwnerResponse("John Doe");
        var account = new AccountResponse(accountId, owner, null, BigDecimal.ZERO);

        when(payAccountBalanceFacade.process(request, accountId)).thenReturn(account);

        var result = accountController.payAccountBalance(accountId, request);

        assertEquals(account, result);
        verify(payAccountBalanceFacade).process(request, accountId);
    }

}