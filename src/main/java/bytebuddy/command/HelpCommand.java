package bytebuddy.command;

import bytebuddy.storage.Storage;
import bytebuddy.task.TaskList;
import bytebuddy.ui.Ui;

public class HelpCommand extends Command {
    public HelpCommand() {
        super(true);
    }

    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui) {
        return ui.showHelpMessage();
    }
}
