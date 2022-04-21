package org.example.volunteers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.example.Sort;
import org.junit.jupiter.api.Test;

public class GetOccurenceByEmail {

  @Test
  public void getOccurenceByEmail_WithSameEmail() {
    // Arrange - Given
    Volunteer volunteer = new Volunteer("", "", "", "gaylord.manaudou@example.org", "0065555734");

    List<Volunteer> volunteers = Arrays.asList(
        new Volunteer("Frog", "MAN", "", "gaylord.manaudou@example.org", "0065555794"),
        new Volunteer("Gayyyylord", "MANAU DOU", "", "gaylord.manaudou@example.fr", "0065555744"),
        new Volunteer("John", "DOE", "", "John.DOE@example.org", "0065555614"));

    // Act - When
    List<Volunteer> result = Sort.getOccurenceByEmail(volunteer, volunteers);
    List<Volunteer> resultExpected = Arrays.asList(
        new Volunteer("Frog", "MAN", "", "gaylord.manaudou@example.org", "0065555794"));

    // Assert - Then
    assertEquals(resultExpected, result);
  }

  @Test
  public void getOccurenceByEmail_WithSameEmail_TwoResult() {
    // Arrange - Given
    Volunteer volunteer = new Volunteer("", "", "", "gaylord.manaudou@example.org", "0065555734");

    List<Volunteer> volunteers = Arrays.asList(
        new Volunteer("Frog", "MAN", "", "gaylord.manaudou@example.org", "0065555794"),
        new Volunteer("Gayyyylord", "MANAU DOU", "bill", "gaylord.manaudou@example.org", "0065555744"),
        new Volunteer("John", "DOE", "", "John.DOE@example.org", "0065555614"));

    // Act - When
    List<Volunteer> result = Sort.getOccurenceByEmail(volunteer, volunteers);
    List<Volunteer> resultExpected = Arrays.asList(
        new Volunteer("Frog", "MAN", "", "gaylord.manaudou@example.org", "0065555794"),
        new Volunteer("Gayyyylord", "MANAU DOU", "bill", "gaylord.manaudou@example.org", "0065555744"));

    // Assert - Then
    assertEquals(resultExpected, result);
  }

  @Test
  public void getOccurenceByEmail_WithEmptyEmail() {
    // Arrange - Given
    Volunteer volunteer = new Volunteer("Bibi", "DODO", "", "", "0065555734");

    List<Volunteer> volunteers = Arrays.asList(
        new Volunteer("Frog", "MAN", "", "gaylord.manaudou@example.org", "0065555739"),
        new Volunteer("Bibi", "DODO", "", "", "0102030405"),
        new Volunteer("John", "DOE", "", "John.DOE@example.org", "0908070605"));

    // Act - When
    List<Volunteer> result = Sort.getOccurenceByEmail(volunteer, volunteers);

    // Assert - Then
    assertEquals(0, result.size());
  }

}
