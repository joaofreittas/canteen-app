package uninter.app.canteen.entrypoint.api.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;

@Builder
public record AccountResponse(
    String id,
    OwnerResponse owner,
    List<PurchaseResponse> purchases,
    BigDecimal totalBalance) {

}
