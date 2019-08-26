import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        //Greeting the user
        Greeting();
        //Create an dynamic ArrayList for tasks
        ArrayList<Task> tasks = new ArrayList<>();
        while (true) {
            Scanner in = new Scanner(System.in);
            String input = in.nextLine();

            //Split the command line to command and description
            String[] splitCommand = input.split(" ", 2);
            String command = splitCommand[0];
            String description = "";

            if (splitCommand.length >= 2) {
                description = splitCommand[1];
            }
            try {
                if (command.equals("done") || command.equals("todo") || command.equals("event") || command.equals("deadline")) {
                    if (description.equals("")) {
                        throw new DukeException("\u2639 OOPS!!! The description of a " + command + " cannot be empty.");
                    }
                }
                if (input.equals("bye")) {
                    Bye();
                    break;
                } else if (input.equals("list")) {
                    ListTasks(tasks);
                } else if (command.equals("done")) {
                    DoneCommand(tasks, description);
                } else if (command.equals("todo")) {
                    AddToDo(tasks, description);
                } else if (command.equals("deadline")) {
                    AddDeadline(tasks, description);
                } else if (command.equals("event")) {
                    AddEvent(tasks, description);
                } else {
                    // AddTask(tasks, description);
                    throw new DukeException("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                String boundary = "    ____________________________________________________________";
                String padding = "     ";
                System.out.println(boundary);
                System.out.println(padding + e.getMessage());
                System.out.println(boundary);
                System.out.println();
            }


        }
    }

    public static void Greeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String boundary = "    ____________________________________________________________";
        String padding = "     ";
        System.out.println("Hello from\n" + logo);
        System.out.println(boundary);
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        System.out.println(boundary);
        System.out.println();
    }

    public static void Bye() {
        String boundary = "    ____________________________________________________________";
        System.out.println(boundary);
        System.out.println("     " + "Bye. Hope to see you again soon!");
        System.out.println(boundary);
        System.out.println();
    }

    public static void ListTasks(ArrayList<Task> tasks) {
        String boundary = "    ____________________________________________________________";
        String padding = "     ";
        System.out.println(boundary);
        System.out.println(padding + "Here are the tasks in your list:");
        for (int i = 1; i <= tasks.size(); i++) {
            Task currentTask = tasks.get(i - 1);
            System.out.println(padding + i + ". " + currentTask);
        }
        System.out.println(boundary);
        System.out.println();
    }

    public static void DoneCommand(ArrayList<Task> tasks, String description) {
        String boundary = "    ____________________________________________________________";
        String padding = "     ";
        System.out.println(boundary);
        int commandNumber = Integer.parseInt(description);
        System.out.println(boundary);
        System.out.println("     Nice! I've marked this task as done:");
        //we need to get the reference for the task in the array
        Task currentTask = tasks.get(commandNumber - 1);
        currentTask.markAsDone();
        System.out.println(padding + "  " + currentTask);
        System.out.println(boundary);
        System.out.println();
    }
    public static void AddTask(ArrayList<Task> tasks, String input) {
        String boundary = "    ____________________________________________________________";
        String padding = "     ";
        tasks.add(new Task(input));
        System.out.println(boundary);
        System.out.println(padding + "added: " + input);
        System.out.println(boundary);
        System.out.println();
    }

    public static void AddToDo(ArrayList<Task> tasks, String description) {
        String boundary = "    ____________________________________________________________";
        String padding = "     ";
        ToDo newToDo = new ToDo(description);
        tasks.add(newToDo);
        System.out.println(boundary);
        System.out.println(padding + "Got it. I've added this task:");
        System.out.println(padding + "  " + newToDo);
        System.out.println(padding + "Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(boundary);
        System.out.println();
    }


    public static void AddDeadline(ArrayList<Task> tasks, String description) {
        String boundary = "    ____________________________________________________________";
        String padding = "     ";
        String SplitString[] = description.split(" /by ", 2);
        Deadline newDeadline = new Deadline(SplitString[0], SplitString[1]);
        tasks.add(newDeadline);
        System.out.println(boundary);
        System.out.println(padding + "Got it. I've added this task:");
        System.out.println(padding + "  " + newDeadline);
        System.out.println(padding + "Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(boundary);
        System.out.println();
    }

    public static void AddEvent(ArrayList<Task> tasks, String description) {
        String boundary = "    ____________________________________________________________";
        String padding = "     ";
        String SplitString[] = description.split(" /at ", 2);
        Event newEvent = new Event(SplitString[0], SplitString[1]);
        tasks.add(newEvent);
        System.out.println(boundary);
        System.out.println(padding + "Got it. I've added this task:");
        System.out.println(padding + "  " + newEvent);
        System.out.println(padding + "Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(boundary);
        System.out.println();
    }

    private static void validateInput(String command, String description) throws DukeException{
        if (command == "done" || command == "todo" || command == "event" || command == "deadline") {
            if (description == "") {
                throw new DukeException(command);
            }
        }
    }
}

class Task {
    protected String description;
    protected String type = "";
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[\u2713]" : "[\u2718]"); //return tick or X symbols
    }

    public String getDescription() {
        return this.description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + this.type + " " + this.description;
    }

}

//tasks without any date/time attached to it
class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

//tasks that need to be done before a specific date/time
class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }


}

//tasks that start at a specific time and ends at a specific time
class Event extends Task {
    protected String duration;

    public Event(String description, String duration) {
        super(description);
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + duration + ")";
    }
}

class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
}





