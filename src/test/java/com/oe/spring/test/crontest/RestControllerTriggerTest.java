package com.oe.spring.test.crontest;

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
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestControllerTriggerTest {

    @Autowired
    protected WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    ResultActions performCreateTriggerAction(URI uri) throws Exception {
        return this.mockMvc.perform(post(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE));
    }

    @Test
    public void shouldGetIsFresherEqTrue() throws Exception {
        URI uri = UriComponentsBuilder.fromPath("/trigger").build().encode().toUri();
        performCreateTriggerAction(uri)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isFresher").value(true));
    }
}
