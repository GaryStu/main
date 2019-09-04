import java.util.ArrayList;

public class AddCommand extends Command {
    private Task task;

    AddCommand(Task task) {
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
