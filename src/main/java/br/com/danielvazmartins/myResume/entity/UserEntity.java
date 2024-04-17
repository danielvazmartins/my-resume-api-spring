package br.com.danielvazmartins.myResume.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import br.com.danielvazmartins.myResume.dto.CreateUserDTO;
import lombok.Data;

@Data
@Document(collection = "users")
public class UserEntity {

    @Id
    private String id;
    private String name;
    private String email;
    private String password;

    public UserEntity(String id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public UserEntity(CreateUserDTO user) {
        this.name = user.name();
        this.email = user.email();
        this.password = user.password();
    }
}
