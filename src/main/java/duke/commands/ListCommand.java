package duke.commands;
import duke.tasks.*;
import duke.ui.Ui;
import duke.storage.Storage;
import java.util.ArrayList;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> currentTasks = tasks.getTasks();
        ui.showList(currentTasks);
    }
}
