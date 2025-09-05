package bytebuddy.command;

import bytebuddy.storage.Storage;
import bytebuddy.task.TaskList;
import bytebuddy.ui.Ui;

public class ListCommand extends Command {
    public ListCommand() {
        super(false);
    }

    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui) {
        return ui.getTaskListMessage(tasks);
    }
}
