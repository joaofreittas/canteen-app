package uninter.app.canteen.entrypoint.api.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PurchaseResponse(BigDecimal amount, LocalDateTime purchasedIn) {

}
