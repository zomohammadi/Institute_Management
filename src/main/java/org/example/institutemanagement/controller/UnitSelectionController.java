package org.example.institutemanagement.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.institutemanagement.dto.ResponseStudentDto;
import org.example.institutemanagement.dto.SelectUnitDto;
import org.example.institutemanagement.service.UnitSelectionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/findStudentsWithScore/{courseId}")
    public ResponseEntity<List<ResponseStudentDto>> findStudentsWithScore(@PathVariable("courseId")
                                                                          Long courseId) {

        return new ResponseEntity<>(unitSelectionService.findStudentsWithScore(courseId), HttpStatus.OK);
    }
}
