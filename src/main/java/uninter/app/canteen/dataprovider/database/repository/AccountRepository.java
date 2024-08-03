package uninter.app.canteen.dataprovider.database.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import uninter.app.canteen.dataprovider.database.entities.AccountEntity;

public interface AccountRepository extends MongoRepository<AccountEntity, String> {

}
