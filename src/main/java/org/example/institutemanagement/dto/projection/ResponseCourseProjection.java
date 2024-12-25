package org.example.institutemanagement.dto.projection;

public interface ResponseCourseProjection {
    Long getCourseId();
    String getLessonName();
    String getDay();
    Integer getStartHour();
    Integer getEndHour();
}
