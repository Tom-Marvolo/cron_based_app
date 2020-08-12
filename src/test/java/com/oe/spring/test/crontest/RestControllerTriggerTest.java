package com.oe.spring.test.crontest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oe.spring.test.crontest.dao.entity.StudentEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class RestControllerTriggerTest {

    @Autowired
    protected WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        objectMapper = new ObjectMapper();
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    ResultActions performCreateTriggerAction(URI uri) throws Exception {
        return this.mockMvc.perform(post(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE));
    }

    @Test
    public void shouldGetIsFresherEqTrue() throws Exception {
        URI uri = UriComponentsBuilder.fromPath("/trigger").build().encode().toUri();
        ResultActions resultActions = performCreateTriggerAction(uri);
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        StudentEntity studentEntity = objectMapper.readValue(responseBody, StudentEntity.class);
        log.info("student entity received from restController: {}", studentEntity);
        Assert.assertTrue(studentEntity.getIsFresher());
    }
}
