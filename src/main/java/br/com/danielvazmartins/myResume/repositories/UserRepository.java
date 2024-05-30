package br.com.danielvazmartins.myResume.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.danielvazmartins.myResume.entities.User;

public interface UserRepository extends MongoRepository<User, String> {

    public UserDetails findByEmail(String email);
}
