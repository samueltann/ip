package bytebuddy.command;

import bytebuddy.storage.Storage;
import bytebuddy.task.TaskList;
import bytebuddy.ui.Ui;

public class UnknownCommand extends Command {
    public UnknownCommand() {
        super(true);
    }

    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui) {
        return ui.getErrorMessage("Error: Unknown command");
    }
}
