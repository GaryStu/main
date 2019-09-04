package commands;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;
import storage.Storage;
import java.util.ArrayList;

public class DeleteCommand extends Command {
    private int index;
    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> currentTasks = tasks.getTasks();
        Task currentTask = currentTasks.get(index - 1);
        tasks.delete(index);
        ui.showDeleted(currentTask, currentTasks);
        storage.updateFile(currentTasks);
    }
}
