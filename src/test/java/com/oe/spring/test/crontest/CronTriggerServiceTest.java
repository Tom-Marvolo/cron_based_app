package com.oe.spring.test.crontest;

import com.oe.spring.test.crontest.cron.CronTriggerService;
import com.oe.spring.test.crontest.dao.entity.StudentEntity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CronTriggerServiceTest {

    @SpyBean
    private CronTriggerService cronTriggerService;

    @Test
    public void jobRuns() throws InterruptedException {
        // giving it 5 seconds to cron service to complete the job
        TimeUnit.SECONDS.sleep(5);
        // verify that the cron was executed at least one
        verify(cronTriggerService, atLeastOnce()).updateExistedStudent();
        // get the entity pulled within cron service execution
        StudentEntity studentEntity = cronTriggerService.getStudentEntity();
        // assert that transition field eq null even for saved entity
        Assert.assertNull(studentEntity.getIsFresher());
    }
}
