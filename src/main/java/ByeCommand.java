public class ByeCommand extends Command{
    ByeCommand() {
        super(true);
    }

    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) {
        ui.printFarewell();
    }
}
