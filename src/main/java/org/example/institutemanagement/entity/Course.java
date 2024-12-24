package org.example.institutemanagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.example.institutemanagement.enumaration.Day;

@Entity
@SuperBuilder

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Course extends BaseEntity{

    private Integer capacity;

    private Integer startHour;

    private Integer endHour;

    private Day day;

    @ManyToOne(fetch = FetchType.LAZY)
    private Teacher teacher;

    @ManyToOne(fetch = FetchType.LAZY)
    private Term term;

    @ManyToOne(fetch = FetchType.LAZY)
    private Lesson lesson;

  /*  @ManyToOne(fetch = FetchType.LAZY)
    private Department department;*/
}
