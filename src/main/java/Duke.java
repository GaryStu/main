import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

public class Duke {
    public static void main(String[] args) {
        //Greeting the user
        Greeting();
        //Create an dynamic ArrayList for tasks
        ArrayList<Task> tasks = new ArrayList<>();
        LoadFile(tasks);
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
        UpdateFile(tasks);
        System.out.println(padding + "  " + currentTask);
        System.out.println(boundary);
        System.out.println();
    }
    public static void AddTask(ArrayList<Task> tasks, String input) {
        String boundary = "    ____________________________________________________________";
        String padding = "     ";
        tasks.add(new Task(input));
        UpdateFile(tasks);
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
        UpdateFile(tasks);
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
        UpdateFile(tasks);
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
        UpdateFile(tasks);
        System.out.println(boundary);
        System.out.println(padding + "Got it. I've added this task:");
        System.out.println(padding + "  " + newEvent);
        System.out.println(padding + "Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(boundary);
        System.out.println();
    }

    public static void LoadFile(ArrayList<Task> tasks) {
        String fileName = ".\\Data\\duke.txt";
        String line = null;

        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                ParseLine(line, tasks);
            }

            bufferedReader.close();
        }
        catch(FileNotFoundException e) {
            System.out.println("Unable to open file '" + fileName + "'");
        } catch (IOException e) {
            System.out.println("Error reading file '" + fileName + "'");
            e.printStackTrace();
        }
    }

    public static void ParseLine(String line, ArrayList<Task> tasks) {
        String[] splitLine = line.split(" \\| ");
        String taskType = splitLine[0];
        boolean isDone = splitLine[1].equals("1");
        String description = splitLine[2];

        String timeFrame = "";
        if (taskType.equals("D") || taskType.equals("E")) {
            timeFrame = splitLine[3];
        }
        if (taskType.equals("T")) {
            ParseToDo(tasks, description, isDone);
        } else if (taskType.equals("D")) {
            ParseDeadline(tasks, description, timeFrame, isDone);
        } else if (taskType.equals("E")) {
            ParseEvent(tasks, description, timeFrame, isDone);
        }
    }

    public static void ParseToDo(ArrayList<Task> tasks, String description, boolean isDone) {
        ToDo newToDo = new ToDo(description);
        if (isDone) {
            newToDo.markAsDone();
        }
        tasks.add(newToDo);
    }
    public static void ParseDeadline(ArrayList<Task> tasks, String description, String by, boolean isDone) {
        Deadline newDeadline = new Deadline(description, by);
        if (isDone) {
            newDeadline.markAsDone();
        }
        tasks.add(newDeadline);
    }

    public static void ParseEvent(ArrayList<Task> tasks, String description, String duration, boolean isDone) {
        Event newEvent = new Event(description, duration);
        if (isDone) {
            newEvent.markAsDone();
        }
        tasks.add(newEvent);
    }

    public static void UpdateFile(ArrayList<Task> tasks) {
        String filePath= ".\\Data\\duke.txt";
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (int i = 0; i < tasks.size(); i++) {
                Task currentTask = tasks.get(i);
                String currentLine = currentTask.toString();
                if (i > 0) {
                    bufferedWriter.newLine();
                }
                String status = "0";
                if (currentTask.isDone) {
                    status = "1";
                }

                bufferedWriter.write(currentTask.type + " | " + status + " | " + currentTask.description);
                if ((currentTask.type).equals("E")) {
                    String timeFrame = (currentLine.split("at: ", 2))[1];
                    bufferedWriter.write(" | " + timeFrame.substring(0, timeFrame.length() - 1));
                }
                else if ((currentTask.type).equals("D")) {
                    String timeFrame = (currentLine.split("by: ", 2))[1];
                    bufferedWriter.write(" | " + timeFrame.substring(0, timeFrame.length() - 1));
                }
            }

            bufferedWriter.close();
        } catch(IOException e) {
            System.out.println("Error writing to file '" + filePath + "'");
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
        return this.getStatusIcon() + " " + this.description;
        //TODO: refactor this by using type also
    }

}

//tasks without any date/time attached to it
class ToDo extends Task {

    public ToDo(String description) {
        super(description);
        super.type = "T";
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
        super.type = "D";
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
        super.type = "E";
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






