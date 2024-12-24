package org.example.institutemanagement.entity;

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

    private String nationalCode;

    private String mobileNumber;

    private String emailAddress;
}
