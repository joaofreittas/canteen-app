package uninter.app.canteen.dataprovider.database.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "accounts")
public class AccountEntity {

    @Id
    private String id;
    private OwnerEntity owner;
    private List<PurchaseEntity> purchases;
    private BigDecimal totalBalance;

}
