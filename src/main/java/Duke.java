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
        String[] tasks = new String[100];

        int currentIndex = 0;
        while (true) {
            Scanner in = new Scanner(System.in);
            String input = in.nextLine();
            if (input.equals("bye")) {
                Bye();
                break;
            }
            else if (input.equals("list")) {
                ListTasks(tasks);
            }
            else {
                tasks[currentIndex] = input;
                System.out.println(boundary);
                System.out.println("added: " + input);
                System.out.println(boundary);
                System.out.println();
                currentIndex++;
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

    public static void ListTasks(String[] tasks) {
        String boundary = "    ____________________________________________________________";
        System.out.println(boundary);
        for (int i = 1; i <= 100; i++) {
            if (tasks[i - 1] == null || tasks[i - 1].length() == 0) {
                break;
            }
            else {
                System.out.println(i + ". " + tasks[i - 1]);
            }
        }
        System.out.println(boundary);
        System.out.println();
    }


}



