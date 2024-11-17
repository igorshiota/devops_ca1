package com.ca1.igorspetitions;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PetitionController.class)
class PetitionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetAllPetitions() throws Exception {
        mockMvc.perform(get("/petitions/all")) 
                .andExpect(status().isOk())
                .andExpect(content().string("[]"));  // Expecting an empty list initially
    }

    @Test
    void testCreatePetition() throws Exception {
        mockMvc.perform(post("/petitions/create")  
                .param("title", "Save the Rainforest") 
                .param("description", "A petition to protect forests."))  
                .andExpect(status().is3xxRedirection())  
                .andExpect(redirectedUrl("/petitions/all"));  // Ensure it redirects to /petitions/all
    }

    @Test
    void testSignPetition() throws Exception {
        mockMvc.perform(post("/petitions/sign")
                .param("index", "0")
                .param("name", "Jane Doe")
                .param("email", "jane@example.com"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/petitions/view?index=0"));
    }
}
