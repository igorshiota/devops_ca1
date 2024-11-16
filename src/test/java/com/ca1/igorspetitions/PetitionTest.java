package com.ca1.igorspetitions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

class PetitionTest {

    @Test
    void testAddSignatory() {
        Petition petition = new Petition("Save the Rainforest", "A petition to protect forests.");
        Signatory signatory = new Signatory("John Doe", "john@example.com");
        
        petition.addSignatory(signatory);

        assertEquals(1, petition.getSignatoryList().size());
        assertTrue(petition.getSignatoryList().contains(signatory));
    }

    @Test
    void testGetTitle() {
        Petition petition = new Petition("Save the Rainforest", "A petition to protect forests.");
        assertEquals("Save the Rainforest", petition.getTitle());
    }

    @Test
    void testGetSignatoriesEmptyList() {
        Petition petition = new Petition("Clean the Oceans", "A petition to reduce ocean pollution.");
        List<Signatory> signatories = petition.getSignatoryList();

        assertTrue(signatories.isEmpty());
    }
}
