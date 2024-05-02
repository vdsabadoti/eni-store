package tp.enistore.dao.jpa.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import tp.enistore.bo.Category;

public interface CategoryMongoRepository extends MongoRepository<Category, String> {

    public Category findByUid(String uid);

    public void deleteByUid(String uid);

}
