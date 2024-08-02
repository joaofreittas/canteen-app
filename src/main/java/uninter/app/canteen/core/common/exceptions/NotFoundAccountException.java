package uninter.app.canteen.core.common.exceptions;

public class NotFoundAccountException extends RuntimeException {

    private static final String MESSAGE = "account not found for provided id: ";

    public NotFoundAccountException(String accountId) {
        super(MESSAGE + accountId);
    }

}
