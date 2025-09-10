package bytebuddy.command;

import bytebuddy.storage.Storage;
import bytebuddy.task.Task;
import bytebuddy.task.TaskList;
import bytebuddy.ui.Ui;

public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        super(false);
        assert task != null : "Task should not be null";
        this.task = task;
    }

    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui) {
        tasks.addTask(task);
        return ui.getAddedMessage(tasks, task);
    }
}
