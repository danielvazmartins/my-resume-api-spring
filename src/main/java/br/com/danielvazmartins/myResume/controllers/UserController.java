package br.com.danielvazmartins.myResume.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.danielvazmartins.myResume.dto.CreateUserDTO;
import br.com.danielvazmartins.myResume.entities.User;
import br.com.danielvazmartins.myResume.entities.UserRole;
import br.com.danielvazmartins.myResume.repositories.UserRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    @PostMapping
    //@PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<String> create(@RequestBody @Valid CreateUserDTO user) {
        UserDetails hasUser = userRepository.findByEmail(user.email());
        if ( hasUser != null ) {
            return ResponseEntity.badRequest().body("Usuário já existe!");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(user.password());
        User newUser = new User(null, user.name(), user.email(), encryptedPassword, UserRole.USER);
        userRepository.save(newUser);
        String id = newUser.getId();

        URI newUserURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{userId}").buildAndExpand(id).toUri(); 
        return ResponseEntity.created(newUserURI).build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> read(@PathVariable String userId) {
        Optional<User> user = userRepository.findById(userId);

        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<String> findAll() {
        return ResponseEntity.ok("TODO Implement this method");
    }
}
