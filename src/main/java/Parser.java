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
            if (command.equals("bye")) {
                return new ExitCommand();
            } else if (command.equals("todo")) {
                return new
            } else if (command.equals("deadline")) {

            } else if (command.equals("event")) {

            } else if (command.equals("list")) {
                return new ListCommand();
            } else if (command.equals("done")) {
                return (new MarkDoneCommand()).execute();
            } else if (command.equals("find")) {
                return new FindCommand(description);
            }
        }
    }



}
