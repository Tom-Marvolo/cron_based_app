package com.oe.spring.test.crontest.cron;

import com.oe.spring.test.crontest.dao.entity.StudentEntity;
import com.oe.spring.test.crontest.service.StudentService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Getter
public class CronTriggerService {

    private final StudentService studentService;
    private StudentEntity studentEntity;

    @Scheduled(fixedRate = 10000)
    public void updateExistedStudent() {
        studentEntity = studentService.updateExistedStudent(this.getClass().getSimpleName());
    }
}
