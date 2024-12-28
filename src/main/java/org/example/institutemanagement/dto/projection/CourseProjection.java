package org.example.institutemanagement.dto.projection;
@SuppressWarnings("unused")
public interface CourseProjection {
    Long getId();

    String getDay();

    Integer getStartHour();

    Integer getEndHour();

    String getTitle();

}
