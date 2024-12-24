package org.example.institutemanagement.repository;

import org.example.institutemanagement.entity.Student;
import org.example.institutemanagement.entity.Term;
import org.example.institutemanagement.entity.UnitSelection;
import org.example.institutemanagement.enumaration.Day;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UnitSelectionRepository extends JpaRepository<UnitSelection, Long> {

    @Query("""
            Select count(u)>0 from UnitSelection u
            inner join u.student s
            inner join u.course c
            where u.student = :student
            and c.startHour < :courseEndHour and
            c.endHour > :courseStartHour
            and c.term = :courseTerm
            and c.day = :courseDay
            """)
    boolean isSelectCourseInThisTime(Integer courseStartHour,
                                     Integer courseEndHour,
                                     Term courseTerm,
                                     Day courseDay, Student student);
}
