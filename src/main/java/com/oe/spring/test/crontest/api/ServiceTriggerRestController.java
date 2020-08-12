package com.oe.spring.test.crontest.api;

import com.oe.spring.test.crontest.dao.entity.StudentEntity;
import com.oe.spring.test.crontest.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ServiceTriggerRestController {

    private final StudentService studentService;

    @PostMapping("/trigger")
    public StudentEntity createTrigger() {
        return studentService.updateExistedStudent(this.getClass().getSimpleName());
    }
}
