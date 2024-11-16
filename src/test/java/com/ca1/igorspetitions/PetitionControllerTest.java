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
        mockMvc.perform(get("/petitions"))
                .andExpect(status().isOk())
                .andExpect(content().string("[]"));  // Expecting an empty list
    }

    @Test
    void testCreatePetition() throws Exception {
        String newPetition = "{\"title\": \"Save the Rainforest\", \"description\": \"A petition to protect forests.\"}";

        mockMvc.perform(post("/petitions")
                .contentType("application/json")
                .content(newPetition))
                .andExpect(status().isCreated());
    }
}
