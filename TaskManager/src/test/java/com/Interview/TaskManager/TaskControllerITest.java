package com.Interview.TaskManager;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
@SpringBootTest
@AutoConfigureMockMvc
class TaskControllerITest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void fullFlowTest() throws Exception {
        String response = mockMvc.perform(post("/tasks")
                        .contentType("application/json")
                        .content("""
                        {
                          "title": "Integration Test",
                          "dueDate": "2026-02-20"
                        }
                        """))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertTrue(response.contains("Integration Test"));
    }

}