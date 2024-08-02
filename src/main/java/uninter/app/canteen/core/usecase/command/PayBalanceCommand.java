package uninter.app.canteen.core.usecase.command;

import java.math.BigDecimal;

public record PayBalanceCommand(String accountId, BigDecimal amount) {

}
