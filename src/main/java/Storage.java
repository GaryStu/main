import java.io.*;
import java.util.ArrayList;

public class Storage {
    private String filePath;
    private String line = null;
    Storage(String filePath) {
        this.filePath = filePath;
    }

    ArrayList<Task> load() throws DukeException {
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
            throw new DukeException("Unable to open file '" + filePath + "'");
        } catch (IOException e) {
            throw new DukeException("Error reading file '" + filePath + "'");
        }
        return tasks;
    }

    private static void loadFile(String line, ArrayList<Task> tasks) {
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

    //TODO: maybe we can put the errors in the ui file
    void updateFile(ArrayList<Task> tasks) {
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
        } catch (IOException e) {
            System.out.println("Error writing to file '" + filePath + "'");
            e.printStackTrace();
        }
    }
}
