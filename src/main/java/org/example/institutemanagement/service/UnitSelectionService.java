package org.example.institutemanagement.service;

import org.example.institutemanagement.dto.ResponseStudentDto;
import org.example.institutemanagement.entity.UnitSelection;

import java.util.List;

public interface UnitSelectionService {

    void selectUnit(Long studentId, Long courseId);

    List<ResponseStudentDto> findStudentsWithScore(Long courseId);

    UnitSelection findById(Long unitSelectionId);
}
