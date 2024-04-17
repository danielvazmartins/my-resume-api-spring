package br.com.danielvazmartins.myResume.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import br.com.danielvazmartins.myResume.entity.UserEntity;

public interface UserRepository extends MongoRepository<UserEntity, String> {

}
