package bytebuddy.command;

import bytebuddy.storage.Storage;
import bytebuddy.task.TaskList;
import bytebuddy.ui.Ui;

public class ByeCommand extends Command {
    public ByeCommand() {
        super(true);
    }

    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) {
        ui.printFarewell();
    }
}
