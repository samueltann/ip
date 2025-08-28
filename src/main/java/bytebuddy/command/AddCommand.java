package bytebuddy.command;

import bytebuddy.storage.Storage;
import bytebuddy.task.Task;
import bytebuddy.task.TaskList;
import bytebuddy.ui.Ui;

public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        super(false);
        this.task = task;
    }

    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) {
        tasks.addTask(task);
        ui.printAdded(tasks, task);
    }
}
