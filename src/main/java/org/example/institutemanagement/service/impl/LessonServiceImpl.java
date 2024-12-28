package org.example.institutemanagement.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.institutemanagement.dto.RegisterLessonDto;
import org.example.institutemanagement.entity.Lesson;
import org.example.institutemanagement.exception.FoundException;
import org.example.institutemanagement.mapper.LessonMapper;
import org.example.institutemanagement.repository.LessonRepository;
import org.example.institutemanagement.service.LessonService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;

    @Override
    @Transactional
    public void save(RegisterLessonDto dto) {

        if (Integer.parseInt(dto.unit()) > 3 || Integer.parseInt(dto.unit()) < 1)
            throw new RuntimeException("your unit must be between 1 and 3");

        if (lessonRepository.existsByTitle(dto.title()))
            throw new FoundException("this title already exists");

        Lesson lesson = LessonMapper.createLesson(dto);

        lessonRepository.save(lesson);
    }
}
