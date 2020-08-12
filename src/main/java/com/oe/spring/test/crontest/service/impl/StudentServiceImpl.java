package com.oe.spring.test.crontest.service.impl;

import com.oe.spring.test.crontest.dao.StudentRepository;
import com.oe.spring.test.crontest.dao.entity.StudentEntity;
import com.oe.spring.test.crontest.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public StudentEntity updateExistedStudent(String callFrom) {
        StudentEntity s = studentRepository.findAll().stream().findFirst().orElseThrow(RuntimeException::new);
        s.setIsFresher(true);
        s.setFirstName("updated_first_name");
        log.info("new entity before saving: {}", s);
        StudentEntity save = studentRepository.save(s);
        log.info("saved entity: {} from: {} ", save, callFrom);
        return save;
    }
}
