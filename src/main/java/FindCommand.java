import java.util.ArrayList;

public class FindCommand extends Command {
    private String description;

    FindCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        ArrayList<Task> currentTasks = tasks.getTasks();
        for (Task element: currentTasks) {
            String currentTaskString = element.toString();
            if (currentTaskString.contains(description)) {
                matchingTasks.add(element);
            }
        }
        ui.showList(matchingTasks);
    }
}
