package br.com.danielvazmartins.myResume.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.danielvazmartins.myResume.dto.CreateUserDTO;
import br.com.danielvazmartins.myResume.entity.UserEntity;
import br.com.danielvazmartins.myResume.repository.UserRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<CreateUserDTO> create(@Valid CreateUserDTO user) {
        UserEntity newUser = new UserEntity(user);
        userRepository.save(newUser);
        return ResponseEntity.ok(user);
    }
}
