package tp.enistore.dao.jpa;

import tp.enistore.bo.User;

public interface UserDAO {

    public User getUserByMail(String mail);
}
