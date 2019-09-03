import java.util.Scanner;

public class Ui {

    private final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private final String boundary = "    ____________________________________________________________";
    private final String padding = "     ";


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

    void showBye() {
        String boundary = "    ____________________________________________________________";
        System.out.println(boundary);
        System.out.println("     " + "Bye. Hope to see you again soon!");
        System.out.println(boundary);
        System.out.println();
    }

    String readCommand() {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        return input;
    }


}