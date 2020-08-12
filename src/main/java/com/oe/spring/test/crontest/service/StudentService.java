package com.oe.spring.test.crontest.service;

import com.oe.spring.test.crontest.dao.entity.StudentEntity;

public interface StudentService {

    StudentEntity updateExistedStudent(String callFrom);
}
