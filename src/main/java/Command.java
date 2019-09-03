public abstract class Command {
    private boolean isExit;

    public abstract void execute(TaskList tasks, Ui ui, Storage storage);


}
