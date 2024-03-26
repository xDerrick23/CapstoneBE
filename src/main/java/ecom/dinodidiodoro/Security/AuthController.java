package ecom.dinodidiodoro.Security;

import ecom.dinodidiodoro.User.*;
import ecom.dinodidiodoro.exceptions.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    UsersService userService;

    @Autowired
    JWTTools jwtTools;

    @Autowired
    PasswordEncoder bcrypt;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public User saveUser(@RequestBody NewUserPayload body) {

        body.setPassword(bcrypt.encode(body.getPassword()));
        User created = userService.save(body);
        return created;
    }

    @PostMapping("/login")
    public LoginSuccessfullPayload login(@RequestBody UserLoginPayload body) {

        User user = userService.findByEmail(body.getEmail());

        if (bcrypt.matches(body.getPassword(), user.getPassword())) {
            String token = jwtTools.createToken(user);
            return new LoginSuccessfullPayload(token);
        } else {
            throw new UnauthorizedException("Credenziali non valide");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        System.out.println("Logout effettuato con successo");
        return ResponseEntity.ok("Logout effettuato con successo");

    }
}