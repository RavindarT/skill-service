package com.example.skill;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
@WebMvcTest(SkillController.class)
class SkillControllerTest {
    @Autowired MockMvc mvc;
    @MockitoBean SkillService service;
    @Test void listEmployees() throws Exception {
        when(service.list()).thenReturn(List.of());
        mvc.perform(get("/api/skills")).andExpect(status().isOk()).andExpect(content().json("[]"));
    }
    @Test void rejectInvalidPayload() throws Exception {
        mvc.perform(post("/api/skills").contentType(MediaType.APPLICATION_JSON).content("{}"))
            .andExpect(status().isBadRequest());
    }
}
