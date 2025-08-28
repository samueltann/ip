package bytebuddy.command;

import bytebuddy.task.Task;
import bytebuddy.task.TaskList;
import bytebuddy.ui.Ui;
import bytebuddy.storage.Storage;

public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int taskIndex) {
        super(false);
        this.index = taskIndex;
    }

    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) {
        if (index >= 0 && index < tasks.getSize()) {
            Task t = tasks.getTask(index);
            t.markAsNotDone();
            ui.printMarked(t, false);
        }
    }
}
