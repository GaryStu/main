import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    private static final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String boundary = "    ____________________________________________________________";
    private static final String padding = "     ";

    void showWelcome() {
        System.out.println("Hello from\n" + logo);
        System.out.println(boundary);
        System.out.println(padding + "Hello! I'm Duke");
        System.out.println(padding + "What can I do for you?");
        System.out.println(boundary);
        System.out.println();
    }

    void showLine() {
        System.out.println(boundary);
    }

    void showPadding() {
        System.out.print(padding);
    }

    void showBye() {
        String boundary = "    ____________________________________________________________";
        System.out.println(boundary);
        System.out.println("     " + "Bye. Hope to see you again soon!");
        System.out.println(boundary);
        System.out.println();
    }

    void showList(ArrayList<Task> tasks) {
        showLine();
        showPadding();
        System.out.println("Here are the tasks in your list: ");
        for (int i = 1; i <= tasks.size(); i++) {
            Task currentTask = tasks.get(i - 1);
            showPadding();
            System.out.println(i + ". " + currentTask);
        }
        showLine();
        System.out.println();
    }

    void showDone(Task currentTask) {
        showLine();
        showPadding();
        System.out.println("Nice! I've marked this task as done:");
        showPadding();
        System.out.println("  " + currentTask);
        showLine();
        System.out.println();
    }

    void showAdded(Task currentTask, ArrayList<Task> tasks) {
        showLine();
        System.out.println()
    }
    void showDeleted(Task currentTask, ArrayList<Task> tasks) {
        showLine();
        System.out.println("     Noted. I've removed this task:");
        System.out.println("       " + currentTask);
        System.out.println("     Now you have " + tasks.size() + " tasks in the list.");
        showLine();
        System.out.println();
    }

    String readCommand() {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        return input;
    }


}