package ecom.dinodidiodoro.User;


import ecom.dinodidiodoro.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
    @RequestMapping("/users")
    public class UsersController {

        @Autowired
        private UsersService usersService;

        @GetMapping("/all")
        @PreAuthorize("hasAuthority('ADMIN')")
        public List<User> getAllUsers() {
            return usersService.getUsers();
        }

        @GetMapping("/{id}")
        @PreAuthorize("hasAuthority('ADMIN')")
        public User getUserById(@PathVariable UUID id) {
            return usersService.findById(id);
        }

        @GetMapping("/current")
        @PreAuthorize("isAuthenticated()")
        public User getCurrentUser() {
            UUID currentUserId = usersService.getCurrentUserId();
            return usersService.findById(currentUserId);
        }

        @PutMapping("/{id}/change-role")
        @PreAuthorize("hasAuthority('ADMIN')")
        public ResponseEntity<User> changeRole(@PathVariable UUID id) {
            try {
                usersService.changeRole(id);
                User updatedUser = usersService.findById(id);
                return new ResponseEntity<>(updatedUser, HttpStatus.OK);
            } catch (NotFoundException e) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

        @GetMapping("/email/{email}")
        @PreAuthorize("hasAuthority('ADMIN')")
        public User getUserByEmail(@PathVariable String email) {
            return usersService.findByEmail(email);
        }


    }

