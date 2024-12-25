package org.example.institutemanagement.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.institutemanagement.dto.EvaluationDto;
import org.example.institutemanagement.entity.UnitSelection;
import org.example.institutemanagement.exception.NotFoundException;
import org.example.institutemanagement.repository.UnitSelectionRepository;
import org.example.institutemanagement.service.EvaluationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class EvaluationServiceImpl implements EvaluationService {

    private final UnitSelectionRepository unitSelectionRepository;

    @Override
    @Transactional
    public void gradingStudent(EvaluationDto dto) {

        UnitSelection unitSelection = unitSelectionRepository
                .findById(dto.unitSelectionId()).orElseThrow(
                        () -> new NotFoundException(
                                "unit selection not found")
                );

        unitSelection.setScore(dto.score());
        unitSelectionRepository.save(unitSelection);

    }
}
