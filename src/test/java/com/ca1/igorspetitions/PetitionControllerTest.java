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
        // Perform a GET request to /petitions/all
        mockMvc.perform(get("/petitions/all")) 
                .andExpect(status().isOk())  // Expecting a 200 OK response
                .andExpect(view().name("viewAll"))  // The controller returns the "viewAll" view name
                .andExpect(model().attributeExists("petitions"))  // Check if the "petitions" attribute is added to the model
                .andExpect(model().attribute("petitions", org.hamcrest.Matchers.hasSize(2)));  // Check if the model contains 2 petitions initially
    }

    @Test
    void testCreatePetition() throws Exception {
        // Perform a POST request to create a new petition
        mockMvc.perform(post("/petitions/create")  
                .param("title", "Save the Rainforest") 
                .param("description", "A petition to protect forests."))  
                .andExpect(status().is3xxRedirection())  // Expecting a redirect after creation
                .andExpect(redirectedUrl("/petitions/all"));  // Redirect to /petitions/all after creating
    }

    @Test
    void testSignPetition() throws Exception {
        // Perform a POST request to sign a petition
        mockMvc.perform(post("/petitions/sign")
                .param("index", "0")
                .param("name", "Jane Doe")
                .param("email", "jane@example.com"))
                .andExpect(status().is3xxRedirection())  // Expecting a redirect after signing the petition
                .andExpect(redirectedUrl("/petitions/view?index=0"));  // Redirect to petition view after signing
    }
}
