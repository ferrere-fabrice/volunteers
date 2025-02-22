package org.example.volunteers;

import static org.junit.jupiter.api.Assertions.*;

import org.example.Format;

import org.junit.jupiter.api.Test;

public class CapitalizeTest {

    @Test
    public void testCapitalizeFirstName() {
        // Arrange - Given
        String firstName = "john";
        // Act - When
        String result = Format.toCapitalize(firstName);

        // Assert - Then
        assertEquals(result, "John");
    }

    @Test
    public void testCapitalizeFirstName_EmptyString() {
        // Arrange - Given
        String firstName = "";

        // Act - When
        String result = Format.toCapitalize(firstName);

        // Assert - Then
        assertEquals(result, "");
    }

    @Test
    public void testCapitalizeFirstName_UppercaseToCapitalize() {
        // Arrange - Given
        String firstName = "JOSE";

        // Act - When
        String result = Format.toCapitalize(firstName);

        // Assert - Then
        assertEquals(result, "Jose");
    }
}
