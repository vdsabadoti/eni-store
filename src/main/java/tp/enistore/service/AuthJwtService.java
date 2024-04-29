package tp.enistore.service;

import org.springframework.stereotype.Service;
import tp.enistore.bo.User;
import tp.enistore.dao.jpa.UserDAO;
import tp.enistore.security.JwtService;

@Service
public class AuthJwtService {

    private UserDAO userDAO;
    private JwtService jwtService;

    AuthJwtService(UserDAO userDAO, JwtService jwtService) {
        this.userDAO = userDAO;
        this.jwtService = jwtService;
    }

    public User findUserByMail(String mail){
        return userDAO.getUserByMail(mail);
    }

    public String generateToken(User user) {
        User loadUser = userDAO.getUserByMail(user.getMail());
        if (loadUser == null){
            return "User not found";
        }
        return jwtService.generateToken(loadUser);
    }
}
