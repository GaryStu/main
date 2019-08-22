import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        //Greeting the user
        String boundary = "    ____________________________________________________________";
        System.out.println("Hello from\n" + logo);
        System.out.println(boundary);
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        System.out.println(boundary);
        System.out.println();
        echo();
    }

    public static void echo() {
        String boundary = "    ____________________________________________________________";
        while (true) {
            Scanner in = new Scanner(System.in);
            String input = in.nextLine();
            if (input.equals("bye")) {
                System.out.println(boundary);
                System.out.println("     " + "Bye. Hope to see you again soon!");
                System.out.println(boundary);
                System.out.println();
                break;
            }
            else {
                System.out.println(boundary);
                System.out.println("     " + input);
                System.out.println(boundary);
                System.out.println();
            }
        }


    }

}


