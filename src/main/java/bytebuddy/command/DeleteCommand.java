package bytebuddy.command;

import bytebuddy.storage.Storage;
import bytebuddy.task.Task;
import bytebuddy.task.TaskList;
import bytebuddy.ui.Ui;

public class DeleteCommand extends Command {

    private final String body;

    public DeleteCommand(String body) {
        super(false);
        this.body = body;
    }

    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui) {
        if (body.isEmpty()) {
            return ui.getErrorMessage("Error: Empty body.");
        }
        try {
            int idx = Integer.parseInt(body) - 1;
            if (idx >= 0 && idx < tasks.getSize()) {
                Task removed = tasks.removeTask(idx);
                assert removed != null : "Task should not be null";
                return ui.getDeletedMessage(tasks, removed);
            } else {
                return ui.getErrorMessage("Error: Invalid task index.");
            }
        } catch (NumberFormatException e) {
            return ui.getErrorMessage("Error: Please provide a valid task number to delete.");
        }
    }
}