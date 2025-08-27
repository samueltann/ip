public class UnknownCommand extends Command {
    public UnknownCommand() {
        super(true);
    }

    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) {
        ui.printError("Error: Unknown command");
    }
}
