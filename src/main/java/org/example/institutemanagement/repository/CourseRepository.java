package org.example.institutemanagement.repository;

import org.example.institutemanagement.dto.projection.ResponseCourseProjection;
import org.example.institutemanagement.entity.Course;
import org.example.institutemanagement.enumaration.Day;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("""
            Select c.id as courseId, l.title as lessonName, c.day as day
            , c.startHour as startHour , c.endHour as endHour
            from Course c
            join c.term t
            join c.lesson l where c.teacher.id = :teacherId
            and t.id = :termId
            """
    )
    List<ResponseCourseProjection> findCourses(Long teacherId, Long termId);

    @Query("""
            SELECT c FROM Course c
            WHERE c.term.id = :termId
            AND c.teacher.id = :teacherId
            AND c.day = :day
            AND (
                (c.startHour < :endHour AND c.endHour > :startHour)
            )
            """)
    List<Course> findConflictingCourses(@Param("startHour") Integer startHour,
                                        @Param("endHour") Integer endHour,
                                        @Param("day") Day day,
                                        @Param("termId") Long termId,
                                        @Param("teacherId") Long teacherId);
}
