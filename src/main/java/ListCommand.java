public class ListCommand extends Command {
    ListCommand() {
        super(false);
    }

    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) {
        ui.printTaskList(tasks);
    }
}
