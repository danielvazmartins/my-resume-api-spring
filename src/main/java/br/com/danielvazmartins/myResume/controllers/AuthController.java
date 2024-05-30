package br.com.danielvazmartins.myResume.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.danielvazmartins.myResume.dto.RequestLoginDTO;
import br.com.danielvazmartins.myResume.dto.ResponseLoginDTO;
import br.com.danielvazmartins.myResume.entities.User;
import br.com.danielvazmartins.myResume.services.TokenService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    TokenService tokenService;

    @PostMapping("login")
    public ResponseEntity<ResponseLoginDTO> login(@RequestBody @Valid RequestLoginDTO requestLogin) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(requestLogin.email(), requestLogin.password());
        Authentication auth = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        String token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new ResponseLoginDTO(token));
    }
}
