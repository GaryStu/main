import java.io.*;
import java.util.ArrayList;

public class Storage {
    private String filePath;
    private String line = null;
    Storage(String filePath) {
        this.filePath = filePath;
    }

    ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null) {
                //TODO: Parse the line
                loadFile(line, tasks);
            }
            bufferedReader.close();

        } catch(FileNotFoundException e) {
            System.out.println("Unable to open file '" + filePath + "'");
        } catch (IOException e) {
            System.out.println("Error reading file '" + filePath + "'");
        }
        return tasks;
    }

    public static void loadFile(String line, ArrayList<Task> tasks) {
        String[] splitLine = line.split(" \\| ");
        String taskType = splitLine[0];
        boolean isDone = splitLine[1].equals("1");
        String description = splitLine[2];

        String timeFrame = "";
        if (taskType.equals("D") || taskType.equals("E")) {
            timeFrame = splitLine[3];
        }
        if (taskType.equals("T")) {
            loadToDo(tasks, description, isDone);
        } else if (taskType.equals("D")) {
            loadDeadline(tasks, description, timeFrame, isDone);
        } else if (taskType.equals("E")) {
            loadEvent(tasks, description, timeFrame, isDone);
        }

    }

    //TODO: make such that the loadFile only need to call one function only
    private static void loadToDo(ArrayList<Task> tasks, String description, boolean isDone) {
        ToDo newToDo = new ToDo(description);
        if (isDone) {
            newToDo.markAsDone();
        }
        tasks.add(newToDo);
    }

    private static void loadDeadline(ArrayList<Task> tasks, String description, String by, boolean isDone) {
        Deadline newDeadline = new Deadline(description, by);
        if (isDone) {
            newDeadline.markAsDone();
        }
        tasks.add(newDeadline);
    }

    private static void loadEvent(ArrayList<Task> tasks, String description, String duration, boolean isDone) {
        Event newEvent = new Event(description, duration);
        if (isDone) {
            newEvent.markAsDone();
        }
        tasks.add(newEvent);
    }
}
