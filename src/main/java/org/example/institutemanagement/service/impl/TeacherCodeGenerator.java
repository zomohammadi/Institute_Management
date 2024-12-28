package org.example.institutemanagement.service.impl;

import org.example.institutemanagement.util.GenerateCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class TeacherCodeGenerator {
    public String generateCode() {
        return GenerateCode.generateCode().concat(GenerateCode.generateCode());
    }
}
