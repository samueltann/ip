public class MarkCommand extends Command {
    private final int index;

    MarkCommand(int taskIndex) {
        super(false);
        this.index = taskIndex;
    }

    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) {
        if (index >= 0 && index < tasks.getSize()) {
            Task t = tasks.getTask(index);
            t.markAsDone();
            ui.printMarked(t, true);
        }
    }
}
