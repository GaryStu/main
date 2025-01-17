package parsers;
import commands.*;
import exceptions.DukeException;
import tasks.Deadline;
import tasks.Event;
import tasks.ToDo;

/**
 * Parser is a public class that help to parse the command that is inputted from the user
 * And generate the appropriate command with their appropriate arguments
 */
public class Parser {
    /**
     * This is the main function that parse the command inputted by the user
     * @param fullCommand the string the user input in the CLI
     * @return <code>new ExitCommand()</code> if the user input "bye"
     *         <code>new AddCommand(new ToDo())</code> if the user input "todo" followed by the description of the activity
     *         <code>new AddCommand(new Event()</code> if the user input "event" followed by the time the event is held
     *         <code>new ListCommand()</code> if the user input list
     *         <code>new MarkDoneCommand(index)</code> if the user input "done" followed by the index of the task to be marked done
     *         <code>new FindCommand(description)</code> if the user input "find" followed by the string that needs to be added
     *         <code>new DeleteCommand(index) </code> if the sure input "delete" followed by the index of the task to be deleted
     * @throws DukeException either there is no description in "done", "todo", "event", and "deadline" command
     *                       or the command is not recognized
     */
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
