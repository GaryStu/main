package duke.tasks;

import java.util.ArrayList;
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
    public TaskList() {;}
    void add(Task newTask) { (this.tasks).add(newTask);}
    void add(ToDo newToDo) {
        (this.tasks).add(newToDo);
    }
    void add(Deadline newDeadline) {
        (this.tasks).add(newDeadline);
    }
    void add(Event newEvent) {
        (this.tasks).add(newEvent);
    }

    public void delete(int index) { (this.tasks).remove(index - 1);}

    public ArrayList<Task> getTasks() {
        return tasks;
    }

}
