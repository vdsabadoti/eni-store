package tp.enistore.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import tp.enistore.dao.jpa.UserDAO;

@Service
public class MongoUserDetailsService implements UserDetailsService {

    private UserDAO userDAO;

    public MongoUserDetailsService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userDAO.getUserByMail(username);
    }
}
