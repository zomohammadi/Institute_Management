package org.example.institutemanagement.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.institutemanagement.dto.RegisterTermDto;
import org.example.institutemanagement.service.TermService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/terms")
@RequiredArgsConstructor
public class TermController {

    private final TermService termService;

    @PostMapping("/TermRegister")
    public ResponseEntity<Void> termRegister(@RequestBody @Valid RegisterTermDto dto) {

        termService.save(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }
}
