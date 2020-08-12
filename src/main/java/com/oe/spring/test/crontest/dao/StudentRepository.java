package com.oe.spring.test.crontest.dao;

import com.oe.spring.test.crontest.dao.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
}
