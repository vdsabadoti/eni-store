package tp.enistore.security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.HttpRequestHandler;
import tp.enistore.service.MongoUserDetailsService;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    protected final Log logger = LogFactory.getLog(getClass());

    private MongoUserDetailsService mongoUserDetailsService;
    private JwtFilter jwtFilter;

    SecurityConfig(MongoUserDetailsService mongoUserDetailsService, JwtFilter jwtFilter) {
        this.mongoUserDetailsService = mongoUserDetailsService;
        this.jwtFilter = jwtFilter;
    }


    //Injection d'une méthode dans le middleware pour definir la façon de se connecter (logique de connexion)
    @Autowired
    public void configAuthBuilder(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(this.mongoUserDetailsService);
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    /**
     * Restriction des URLs selon la connexion utilisateur et leurs rôles
     */
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // Désactivé Cross Site Request Forgery
        // Non préconisé pour les API REST en stateless.
        // Sauf pour POST, PUT, PATCH et DELETE
        http.csrf(csrf -> {
            csrf.disable();
        });

        //URLs and Roles
        http.authorizeHttpRequests(
                (authorizeRequests) -> {
                    authorizeRequests.
                            requestMatchers(HttpMethod.DELETE, "/api/v1/admin/**").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.DELETE, "/api/v2/admin/**").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.POST, "/api/v1/login").permitAll()
                            .anyRequest().permitAll();
                }
        );

        //Configurer les authentifactions de SpringSecurity
        http.httpBasic(Customizer.withDefaults());

        //Ajouter l'authentification personalisé qu'on a crée
        http.addFilterBefore(this.jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}