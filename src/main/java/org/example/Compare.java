package org.example;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.text.similarity.LevenshteinDistance;
import org.example.volunteers.Volunteer;

public class Compare {

  public static int compareTo(String strA, String strB) {

    if (strA.isBlank() && strB.isBlank()) {
      return 999999;
    }
    strA = Format.removeEspace(strA);
    strB = Format.removeEspace(strB);
    LevenshteinDistance ld = new LevenshteinDistance();
    return ld.apply(strA, strB);
  }

  public static boolean hasCompleteWord(String word, String sentence) {

    Set<String> words = new HashSet<String>(
        Arrays.asList(sentence.split(" ")));

    return words.contains(word);
  }

  public static Volunteer fusion(List<Volunteer> volunteersWithSameEmailOrPhone) {
    String pipeChar = " | ";
    StringBuilder fusionFirstName = new StringBuilder();
    StringBuilder fusionLastName = new StringBuilder();
    StringBuilder fusioNickName = new StringBuilder();
    StringBuilder fusionEmail = new StringBuilder();
    StringBuilder fusionPhone = new StringBuilder();

    volunteersWithSameEmailOrPhone.forEach(vol -> {

      if (!hasCompleteWord(vol.firstName, fusionFirstName.toString()))
        fusionFirstName.append(pipeChar + vol.firstName);

      if (!hasCompleteWord(vol.lastName, fusionLastName.toString()))
        fusionLastName.append(pipeChar + vol.lastName);

      if (!hasCompleteWord(vol.nickName, fusioNickName.toString()))
        fusioNickName.append(pipeChar + vol.nickName);

      if (!hasCompleteWord(vol.eMail, fusionEmail.toString()))
        fusionEmail.append(pipeChar + vol.eMail);

      if (!hasCompleteWord(vol.phone, fusionPhone.toString()))
        fusionPhone.append(pipeChar + vol.phone);
    });

    fusionFirstName.replace(0, pipeChar.length(), "");
    fusionLastName.replace(0, pipeChar.length(), "");
    fusioNickName.replace(0, pipeChar.length(), "");
    fusionEmail.replace(0, pipeChar.length(), "");
    fusionPhone.replace(0, pipeChar.length(), "");

    // return le volunteer fusionner
    return new Volunteer(fusionFirstName.toString(), fusionLastName.toString(), fusioNickName.toString(),
        fusionEmail.toString(), fusionPhone.toString());
  }

  func
}