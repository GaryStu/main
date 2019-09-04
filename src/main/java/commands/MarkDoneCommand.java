package commands;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;
import storage.Storage;
import java.util.ArrayList;

public class MarkDoneCommand extends Command{
    private int index;

    public MarkDoneCommand(int index) {
        this.index = index;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> currentTasks = tasks.getTasks();
        Task currentTask = currentTasks.get(index - 1);
        currentTask.markAsDone();
        storage.updateFile(currentTasks);
        ui.showDone(currentTask);
    }
}
