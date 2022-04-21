package org.example.volunteers;

import java.util.Arrays;

import static java.util.stream.Collectors.joining;

public final class Volunteer {
    public final String firstName;
    public final String lastName;
    public final String nickName;
    public final String eMail;
    public final String phone;

    public Volunteer(
            String firstName,
            String lastName,
            String nickName,
            String eMail,
            String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.eMail = eMail;
        this.phone = phone;
    }

    // generate all getters
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNickName() {
        return nickName;
    }

    public String geteMail() {
        return eMail;
    }

    @Override
    public String toString() {
        return Arrays.stream(new String[] { firstName, lastName, nickName, eMail, phone })
                .map(attribute -> String.format("\"%s\"", attribute))
                .collect(joining(";"));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Volunteer volunteer = (Volunteer) o;

        if (!firstName.equals(volunteer.firstName))
            return false;
        if (!lastName.equals(volunteer.lastName))
            return false;
        if (!nickName.equals(volunteer.nickName))
            return false;
        if (!eMail.equals(volunteer.eMail))
            return false;
        return phone.equals(volunteer.phone);
    }

    public boolean isBlank() {
        if (firstName.isBlank()
                && lastName.isBlank()
                && nickName.isBlank()
                && eMail.isBlank() && phone.isBlank())
            return true;

        return false;
    }
}
