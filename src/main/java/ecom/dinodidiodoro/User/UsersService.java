package ecom.dinodidiodoro.User;

import ecom.dinodidiodoro.exceptions.BadRequestException;
import ecom.dinodidiodoro.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UsersService {


    @Autowired
    UserRepository userRepository;



    public User save(User user) {
        return userRepository.save(user);
    }

    public User save(NewUserPayload body) {
        userRepository.findByEmail(body.getEmail()).ifPresent(user -> {
            throw new BadRequestException("L'email " + body.getEmail() + " è gia stata utilizzata");
        });
        User newUser = new User(body.getName(), body.getSurname(), body.getEmail(), body.getPassword());
        return userRepository.save(newUser);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User findById(UUID id) throws NotFoundException {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Utente con email " + email + " non trovato"));
    }

    public void changeRole(UUID id) throws NotFoundException {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
        if (user.getRole() == Role.USER) {
            user.setRole(Role.ADMIN);
        } else {
            user.setRole(Role.USER);
        }
        userRepository.save(user);
    }

    public UUID getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        return currentUser.getId();
    }



}