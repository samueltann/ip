public abstract class Command {

    boolean isExit;

    Command(boolean isExit) {
        this.isExit = isExit;
    }

    public boolean isExit() {
        return isExit;
    }

    public abstract void execute(Storage storage, TaskList tasks, Ui ui);
}
