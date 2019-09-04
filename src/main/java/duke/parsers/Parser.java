package duke.parsers;
import duke.commands.*;
import duke.exceptions.DukeException;
import duke.tasks.*;
public class Parser {
    public static Command parse(String fullCommand) throws DukeException {
        //TODO: Put error for invalid input and what not
        String[] splitCommand = fullCommand.split(" ", 2);
        String command = splitCommand[0];
        String description = "";

        if (splitCommand.length >= 2) {
            description = splitCommand[1];
        }
        if (command.equals("done") || command.equals("todo") || command.equals("event") || command.equals("deadline")) {
            if (description.equals("")) {
                throw new DukeException("\u2639 OOPS!!! The description of a " + command + " cannot be empty.");
            }
        }
        if (command.equals("bye")) {
            return new ExitCommand();
        } else if (command.equals("todo")) {
            return new AddCommand(new ToDo(description));
        } else if (command.equals("deadline")) {
            String SplitString[] = description.split(" /by ", 2);
            return new AddCommand(new Deadline(SplitString[0], SplitString[1]));
        } else if (command.equals("event")) {
            String SplitString[] = description.split(" /at ", 2);
            return new AddCommand(new Event(SplitString[0], SplitString[1]));
        } else if (command.equals("list")) {
            return new ListCommand();
        } else if (command.equals("done")) {
            int index = Integer.parseInt(description);
            return (new MarkDoneCommand(index));
        } else if (command.equals("find")) {
            return new FindCommand(description);
        } else if (command.equals("delete")) {
            int index = Integer.parseInt(description);
            return new DeleteCommand(index);
        } else {
            throw new DukeException("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }



}
