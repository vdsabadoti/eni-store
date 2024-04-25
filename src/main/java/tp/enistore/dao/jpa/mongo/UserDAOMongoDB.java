package tp.enistore.dao.jpa.mongo;

import org.springframework.stereotype.Component;
import tp.enistore.bo.User;
import tp.enistore.dao.jpa.UserDAO;

@Component
public class UserDAOMongoDB implements UserDAO {

    private UserMongoRepository userMongoRepository;

    UserDAOMongoDB(UserMongoRepository userMongoRepository) {
        this.userMongoRepository = userMongoRepository;
    }

    @Override
    public User getUserByMail(String mail) {
        return this.userMongoRepository.findByMail(mail);
    }
}
