import org.example.Format;
import org.example.Sort;
import org.example.volunteers.Volunteer;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.toList;

public class App {
    public static void main(String[] args) throws IOException {
        Pattern quotes = Pattern.compile("^\"([^\"]*)\"$");
        
        Path path = Paths.get("src/main/resources/data.csv");
        List<Volunteer> inputVolunteers = Files.readAllLines(path).stream()
                .map(string -> Arrays.stream(string.split(";", -1))
                        .map(token -> quotes.matcher(token).replaceAll("$1"))
                        .collect(toList()))
                .map(tokens -> new Volunteer(tokens.get(1), tokens.get(0), tokens.get(2), tokens.get(3), tokens.get(4)))
                .collect(toList());

        List<Volunteer> outputVolunteers = cleanUp(inputVolunteers);

        OutputStream os = new FileOutputStream("src/main/resources/output.csv");
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(os, "UTF-8"));

        outputVolunteers.forEach(volunteer -> {
            writer.println(volunteer);
        });

        writer.close();
    }

    private static List<Volunteer> cleanUp(List<Volunteer> volunteers) {
        // This function should contain your dark magic.
        // For now, it simply returns a copy of the initial list.
        List<Volunteer> formatListVolunteers = new ArrayList<Volunteer>();

        // format the output volunteer with Uppercase, ten phone number, etc ...
        volunteers.forEach(volunteer -> {
            formatListVolunteers.add(Format.toFormatVolunteer(volunteer));
        });

        Comparator<Volunteer> comparator = Comparator.comparing(Volunteer::getLastName)
                .thenComparing(Volunteer::getFirstName)
                .thenComparing(Volunteer::geteMail);

        formatListVolunteers.sort(comparator);


        List<Volunteer> SortListVolunteers = Sort.removeDuplicate(formatListVolunteers);
        List<Volunteer> uniqueListVolunteers = Sort.getUniqueVolunteer(SortListVolunteers);
        return Sort.removeDuplicate(uniqueListVolunteers);
    }
}
