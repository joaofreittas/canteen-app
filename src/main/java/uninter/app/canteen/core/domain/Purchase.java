package uninter.app.canteen.core.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record Purchase(BigDecimal amount, LocalDateTime purchasedIn) {

}
