package org.example.institutemanagement.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.example.institutemanagement.dto.SelectUnitDto;
import org.example.institutemanagement.service.UnitSelectionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/unistSelections")
@RequiredArgsConstructor
public class UnitSelectionController {

    private final UnitSelectionService unitSelectionService;

    @PostMapping("/SelectUnit")
    public ResponseEntity<Void> selectUnit(@RequestBody
                                           @Valid
                                           SelectUnitDto dto) {

        unitSelectionService.selectUnit(dto.studentId(), dto.courseId());

        return new ResponseEntity<>(HttpStatus.CREATED);

    }
}
