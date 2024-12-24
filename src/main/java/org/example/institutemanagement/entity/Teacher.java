package org.example.institutemanagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Entity

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Teacher extends  BaseEntity{

    @Column(unique=true,nullable = false)
    private String code;

    private Double salary;

    @ManyToOne(fetch = FetchType.LAZY)
    private Person person;
}
