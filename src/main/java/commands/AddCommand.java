package commands;
import java.util.ArrayList;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;
import storage.Storage;
public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> currentTasks = tasks.getTasks();
        currentTasks.add(task);
        ui.showAdded(task, currentTasks);
        storage.updateFile(currentTasks);
    }

}