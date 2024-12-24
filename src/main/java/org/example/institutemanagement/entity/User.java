package org.example.institutemanagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Table(name = User.TABLE_NAME)
public class User extends BaseEntity {

    static final String TABLE_NAME = "users";

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    private Person person;
}
