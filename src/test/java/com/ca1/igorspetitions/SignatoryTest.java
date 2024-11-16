package com.ca1.igorspetitions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SignatoryTest {

    @Test
    void testSignatoryCreation() {
        Signatory signatory = new Signatory("Jane Doe", "jane@example.com");

        assertEquals("Jane Doe", signatory.getName());
        assertEquals("jane@example.com", signatory.getEmail());
    }

    @Test
    void testInvalidEmail() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Signatory("John Doe", "invalid-email");
        });

        String expectedMessage = "Invalid email format";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
