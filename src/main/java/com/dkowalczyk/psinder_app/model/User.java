package com.dkowalczyk.psinder_app.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true, length = 100)
    private String name;

    @Column(name = "age")
    private int age;

    @Column(name = "bio", length = 500)
    private String bio;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dog_id", referencedColumnName = "id")
    private Dog dog;

    @Column(name = "password" , nullable = false)
    private String password;

    @Column(name = "role")
    private String role;
}
