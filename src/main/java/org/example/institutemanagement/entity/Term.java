package org.example.institutemanagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Entity
@SuperBuilder

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Term extends BaseEntity {

    @Column(unique = true, nullable = false)
    private Long termNumber;

    private LocalDate startDate;
    private LocalDate endDate;
}
