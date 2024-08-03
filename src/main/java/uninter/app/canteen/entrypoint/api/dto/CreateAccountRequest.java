package uninter.app.canteen.entrypoint.api.dto;

import java.math.BigDecimal;

public record CreateAccountRequest(String ownerName, BigDecimal initialBalance) {

}
