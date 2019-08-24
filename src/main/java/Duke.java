import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        //Greeting the user
        String boundary = "    ____________________________________________________________";
        String padding = "     ";
        System.out.println("Hello from\n" + logo);
        System.out.println(boundary);
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        System.out.println(boundary);
        System.out.println();

        //Create an dynamic ArrayList for tasks
        ArrayList<Task> tasks = new ArrayList<>();
        while (true) {
            Scanner in = new Scanner(System.in);
            String input = in.nextLine();

            //Split the command line to command and description
            String[] splitCommand = input.split(" ", 2);
            String command = splitCommand[0];
            String description = "";
            if (splitCommand.length == 2) {
                description = splitCommand[1];
            }

            if (input.equals("bye")) {
                Bye();
                break;
            } else if (input.equals("list")) {
                System.out.println(boundary);
                System.out.println("Here are the tasks in your list:");
                for (int i = 1; i <= tasks.size(); i++) {
                    //TODO: Get the task for that index
                    Task currentTask = tasks.get(i - 1);
                    String taskStatusIcon = currentTask.getStatusIcon();
                    String taskDescription = currentTask.getDescription();
                    System.out.println(padding + i + ". " + taskStatusIcon + " " + taskDescription);
                }
                System.out.println(boundary);
                System.out.println();
            } else if (command.equals("done")) {
                int commandNumber = Integer.parseInt(description);
                System.out.println(boundary);
                System.out.println("     Nice! I've marked this task as done:");
                //we need to get the reference for the task in the array
                Task currentTask = tasks.get(commandNumber - 1);
                currentTask.markAsDone();
                String taskStatusIcon = currentTask.getStatusIcon();
                String taskDescription = currentTask.getDescription();
                System.out.println(padding + "  " + taskStatusIcon + " " + taskDescription);
                System.out.println(boundary);
                System.out.println();
            } else {
                tasks.add(new Task(input));
                System.out.println(boundary);
                System.out.println(padding + "added: " + input);
                System.out.println(boundary);
                System.out.println();
            }
        }

        }
    public static void Bye() {
        String boundary = "    ____________________________________________________________";
        System.out.println(boundary);
        System.out.println("     " + "Bye. Hope to see you again soon!");
        System.out.println(boundary);
        System.out.println();
    }

    //Level 2
    public static void ListTasks(String[] tasks) {
        String boundary = "    ____________________________________________________________";
        System.out.println(boundary);
        for (int i = 1; i <= 100; i++) {
            if (tasks[i - 1] == null || tasks[i - 1].length() == 0) {
                break;
            } else {
                System.out.println(i + ". " + tasks[i - 1]);
            }
        }
        System.out.println(boundary);
        System.out.println();
    }
}

class Task {
    protected String description;
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

}



