package org.example.volunteers;

import java.util.List;

import org.example.Sort;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

public class GetOccurenceByEMailWithEmptyCompletNameAndFusionTest {

  @Test
  public void GetOccurenceByEMailWithEmptyCompletNameAndFusionTest_UniqueData() {
    // Arrange - Given
    List<Volunteer> fakeUniquevolunteers = Arrays.asList(
        new Volunteer("", "", "Droid", "blanche.mallette@example.org", "0055551065"),
        new Volunteer("Blanche", "MALLETTE", "Droid",
            "blanche.mallette@example.org | droid1567@example.org | droid2290@example.com",
            "0055551065"),
        new Volunteer("Frog", "MAN", "", "gaylord.manaudou@example.org", "0065555734"));

    // Act - When
    List<Volunteer> result = Sort.fusionByEmail(fakeUniquevolunteers);

    // Assert - Then
    assertEquals(
        Arrays.asList(
            new Volunteer("Blanche", "MALLETTE", "Droid",
                "blanche.mallette@example.org | droid1567@example.org | droid2290@example.com",
                "0055551065"),
            new Volunteer("Frog", "MAN", "", "gaylord.manaudou@example.org", "0065555734")),
        result);
  }

}