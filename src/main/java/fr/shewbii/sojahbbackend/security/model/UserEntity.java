package fr.shewbii.sojahbbackend.security.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "SOJA_USER")
@Data
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String role;

}
