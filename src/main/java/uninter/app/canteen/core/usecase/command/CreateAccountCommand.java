package uninter.app.canteen.core.usecase.command;

import java.math.BigDecimal;

public record CreateAccountCommand(String ownerName, BigDecimal initialBalance) {

}
