package org.example.institutemanagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
public class Person extends BaseEntity{

    private String firstName;

    private String lastName;

    @Column(unique=true, nullable=false)
    private String nationalCode;

    @Column( nullable=false)
    private String mobileNumber;

    @Column( nullable=false)
    private String emailAddress;
}
