package bytebuddy.command;

import bytebuddy.exception.MissingDescriptionException;
import bytebuddy.storage.Storage;
import bytebuddy.task.Task;
import bytebuddy.task.TaskList;
import bytebuddy.ui.Ui;

public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        super(false);
        this.keyword = keyword;
    }
    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) {
        if (keyword.isEmpty()) {
            throw new MissingDescriptionException("find");
        }
        TaskList results = tasks.findTasks(keyword);
        ui.printTaskList(results);
    }
}
