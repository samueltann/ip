package bytebuddy.command;

import bytebuddy.storage.Storage;
import bytebuddy.task.Task;
import bytebuddy.task.TaskList;
import bytebuddy.ui.Ui;

public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(int taskIndex) {
        super(false);
        this.index = taskIndex;
    }

    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui) {
        if (index >= 0 && index < tasks.getSize()) {
            Task t = tasks.getTask(index);
            t.markAsDone();
            return ui.getMarkedMessage(t, true);
        }
        return ui.getErrorMessage("Error: Invalid task index.");
    }
}
