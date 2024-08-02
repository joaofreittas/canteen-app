package uninter.app.canteen.core.usecase.command;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record RegisterPurchaseCommand(String accountId, BigDecimal amount) {

}
