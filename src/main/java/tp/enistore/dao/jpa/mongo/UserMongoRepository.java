package tp.enistore.dao.jpa.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import tp.enistore.bo.User;

public interface UserMongoRepository extends MongoRepository<User, String> {

    //Requesting User by mail cause mail is the "username" of SpringSecurity
    public User findByMail(String mail);

}
