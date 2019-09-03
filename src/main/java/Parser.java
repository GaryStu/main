import java.util.ArrayList;

public class Parser {
    public static Command parse(String fullCommand) {
        //TODO: Put error for invalid input and what not
        String[] splitCommand = fullCommand.split(" ", 2);
        String command = splitCommand[0];
        String description = "";

        if (splitCommand.length >= 2) {
            description = splitCommand[1];
        }
        try {
            if (command == "bye") {
                return new ExitCommand();
            } else if (command == "todo") {
                return new
            } else if (command == "deadline") {

            } else if (command == "event") {

            } else if (command == "list") {
                return new ListCommand();
            } else if (command == "done") {
                return new MarkDoneCommand();
            }
        }
    }



}
