package duke.commands;
import duke.tasks.*;
import duke.ui.Ui;
import duke.storage.Storage;
public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    public boolean isExit() {
        return false;
    }
}
