package uninter.app.canteen.dataprovider.database.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PurchaseEntity(BigDecimal amount, LocalDateTime purchasedIn) {

}
