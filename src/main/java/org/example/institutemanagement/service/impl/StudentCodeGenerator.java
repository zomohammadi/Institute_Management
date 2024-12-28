package org.example.institutemanagement.service.impl;

import org.example.institutemanagement.util.GenerateCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class StudentCodeGenerator {
    public String generateCode(Integer enteringYear) {
        return enteringYear.toString().concat(GenerateCode.generateCode());
    }
}
