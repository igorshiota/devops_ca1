package com.ca1.igorspetitions;

import org.junit.jupiter.api.BeforeEach;
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

    @Autowired
    private PetitionController petitionController;  // Autowire the PetitionController

    @BeforeEach
    void setup() {
        // Reset the petitions list to have only the initial 2 petitions
        petitionController.getPetitions().clear();  // Clear any existing petitions
        petitionController.getPetitions().add(new Petition("Save the Forest", "A petition to save the rainforest."));
        petitionController.getPetitions().add(new Petition("Support Animal Rights", "A petition to support animal rights globally."));
    }

    @Test
    void testGetAllPetitions() throws Exception {
        mockMvc.perform(get("/petitions/all")) 
                .andExpect(status().isOk())  // Expecting a 200 OK response
                .andExpect(view().name("viewAll"))  // The controller returns the "viewAll" view name
                .andExpect(model().attributeExists("petitions"))  // Check if the "petitions" attribute is added to the model
                .andExpect(model().attribute("petitions", org.hamcrest.Matchers.hasSize(2)));  // Expect 2 petitions
    }

    @Test
    void testCreatePetition() throws Exception {
        mockMvc.perform(post("/petitions/create")  
                .param("title", "Save the Rainforest") 
                .param("description", "A petition to protect forests."))  
                .andExpect(status().is3xxRedirection())  // Expecting a redirect after creation
                .andExpect(redirectedUrl("/petitions/all"));  // Redirect to /petitions/all after creating
    }

    @Test
    void testSignPetition() throws Exception {
        mockMvc.perform(post("/petitions/sign")
                .param("index", "0")
                .param("name", "Jane Doe")
                .param("email", "jane@example.com"))
                .andExpect(status().is3xxRedirection())  // Expecting a redirect after signing the petition
                .andExpect(redirectedUrl("/petitions/view?index=0"));  // Redirect to petition view after signing
    }
}
