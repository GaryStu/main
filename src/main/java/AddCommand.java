public class AddCommand extends Command {
    private Task task;

    AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showAdded();
    }
}
