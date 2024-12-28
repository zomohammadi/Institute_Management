package org.example.institutemanagement.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.institutemanagement.dto.EvaluationDto;
import org.example.institutemanagement.entity.UnitSelection;
import org.example.institutemanagement.service.EvaluationService;
import org.example.institutemanagement.service.UnitSelectionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class EvaluationServiceImpl implements EvaluationService {

    private final UnitSelectionService unitSelectionService;

    @Override
    @Transactional
    public void gradingStudent(EvaluationDto dto) {

        UnitSelection unitSelection = unitSelectionService
                .findById(dto.unitSelectionId());

        unitSelection.setScore(dto.score());

        unitSelectionService.save(unitSelection);

    }
}
