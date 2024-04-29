package tp.enistore.rest.v1;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tp.enistore.bo.User;
import tp.enistore.security.JwtService;
import tp.enistore.service.AuthJwtService;

@RestController
public class AuthRestController {

    private AuthJwtService authJwtService;

    public AuthRestController(JwtService jwtService, AuthJwtService authJwtService) {
        this.authJwtService = authJwtService;
    }

    @PostMapping("/api/v1/login")
    public String login(
            @RequestBody User user) {

        //loadUser thanks to its mail then
        //call JWT service to generate a Token with user mail
        String token = this.authJwtService.generateToken(user);

        return token;
    }

}
