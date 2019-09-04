package duke.commands;
import duke.tasks.*;
import duke.ui.Ui;
import duke.storage.Storage;
public class ExitCommand extends Command{
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showBye();
    }
}
