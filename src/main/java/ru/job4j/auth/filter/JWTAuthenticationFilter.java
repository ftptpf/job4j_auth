package ru.job4j.auth.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.core.Authentication;
import ru.job4j.auth.model.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@AllArgsConstructor
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public static final String SECRET = "SecretKeyToGenJWTs";
    public static final long EXPIRATION_TIME = 864_000_000; /* 10 days */
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/users/sign-up";

    private AuthenticationManager auth;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) {
        throw AuthenticationException {
            try {
                Person creds = new ObjectMapper()
                        .readValue(req.getInputStream(), Person.class);
                return auth.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                creds.getLogin(),
                                creds.getPassword())
                );
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }



}
