package org.example;

import java.util.List;
import org.example.volunteers.Volunteer;
import java.util.ArrayList;
import java.util.Arrays;

public class Sort {

  static int NUMBER_OF_ERRORS_OF_EMAIL = 4;
  static int NUMBER_OF_ERRORS_OF_LASTNAME_OR_FIRSTNAME = 2;

  public static List<Volunteer> removeDuplicate(List<Volunteer> volunteers) {
    List<Volunteer> result = new ArrayList<>();
    for (Volunteer volunteer : volunteers) {
      if (!result.contains(volunteer)) {
        result.add(volunteer);
      }
    }
    return result;
  }

  public static List<Volunteer> getOccurenceByCompletName(Volunteer volunteer, List<Volunteer> volunteers) {
    if (volunteer.firstName.isBlank() && volunteer.lastName.isBlank())
      return Arrays.asList(volunteer);
    List<Volunteer> result = new ArrayList<>();
    for (Volunteer vol : volunteers) {
      if (Compare.compareTo(volunteer.firstName + volunteer.lastName,
          vol.firstName + vol.lastName) <= NUMBER_OF_ERRORS_OF_LASTNAME_OR_FIRSTNAME) {
        result.add(vol);
      }
    }
    return result;
  }

  public static List<Volunteer> getOccurenceByEmailOrPhoneOrNickname(Volunteer volunteer,
      List<Volunteer> volunteers) {

    List<Volunteer> result = new ArrayList<>();
    for (Volunteer vol : volunteers) {
      if (Compare.compareTo(volunteer.eMail, vol.eMail) <= NUMBER_OF_ERRORS_OF_EMAIL
          || Compare.compareTo(volunteer.phone, vol.phone) == 0
          || Compare.compareTo(volunteer.nickName, vol.nickName) == 0) {
        result.add(vol);
      }
    }
    return result;
  }

  public static List<Volunteer> getUniqueVolunteer(List<Volunteer> formatListVolunteer) {

    // List of volunteers already add in the emptyList
    List<Volunteer> listVolunteerAlreadyAdd = new ArrayList<>();
    List<Volunteer> result = new ArrayList<>();
    // foreach sur formatListVolunteer
    formatListVolunteer.forEach(volunteer -> {
      // sur le s on appel getOccurenceByCompletName et retourne une liste des
      // volontaires avec le meme nom
      List<Volunteer> volunteersByCompletName = Sort.getOccurenceByCompletName(volunteer, formatListVolunteer);
      // Si il n'y a qu'un seul fois le volunteer -> ["Gayylord"]
      if (volunteersByCompletName.size() == 1) {
        // on l'ajoute aux listes des volontaires fait et des volontaires uniques
        listVolunteerAlreadyAdd.add(volunteersByCompletName.get(0));
        result.add(volunteersByCompletName.get(0));
        return;
      }

      // Si il y a plusieurs volontaires avec le même nom -> ["Gayylord", "Gaylord"]
      List<Volunteer> volunteersByEmailOrPhone = Sort.getOccurenceByEmailOrPhoneOrNickname(volunteer,
          volunteersByCompletName);
      if (volunteersByEmailOrPhone.size() == 1) {
        if (listVolunteerAlreadyAdd.contains(volunteer))
          return;

        listVolunteerAlreadyAdd.addAll(volunteersByCompletName);
        result.add(volunteersByEmailOrPhone.get(0));
        return;
      }

      // vérifie si les les utilisateurs ayant le meme nom et (email ou tel)
      // existe dans la liste des utilisateurs déja fusionner ?
      Volunteer volunterFusion = Compare.fusion(volunteersByEmailOrPhone);

      // si l'utilisateur fusionné existe dans la bdd :
      if (listVolunteerAlreadyAdd.contains(volunteer))
        return;
      listVolunteerAlreadyAdd.addAll(volunteersByCompletName);
      // si il existe on ne l'ajoute pas
      result.add(volunterFusion);
    });

    return result;

  }

  public static List<Volunteer> fusionByEmail(List<Volunteer> uniqueListVolunteer) {

    // List of volunteers already add in the emptyList
    List<Volunteer> listVolunteerAlreadyAdd = new ArrayList<>();
    List<Volunteer> result = new ArrayList<>();

    // foreach sur UniqueListVolunteer
    uniqueListVolunteer.forEach(volunteer -> {
      // donne toutes les occurences avec l'email du volunteer

      List<Volunteer> allOccurenceByEmail = getOccurenceByEmail(volunteer, uniqueListVolunteer);
      Volunteer fusion = Compare.fusion(allOccurenceByEmail);
      if (fusion.isBlank())
        return;
      if (listVolunteerAlreadyAdd.contains(fusion))
        return;
      listVolunteerAlreadyAdd.add(fusion);
      result.add(fusion);

    });
    return result;
  }

  public static List<Volunteer> getOccurenceByEmail(Volunteer volunteer, List<Volunteer> volunteers) {

    List<Volunteer> result = new ArrayList<>();
    if (volunteer.eMail.isBlank())
      return result;

    for (Volunteer vol : volunteers) {
      if (Compare.hasCompleteWord(volunteer.eMail, vol.eMail)) {
        result.add(vol);
      }
    }
    return result;
  }
}