public class DeleteCommand extends Command {

    private final String body;

    DeleteCommand(String body) {
        super(false);
        this.body = body;
    }

    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) {
        if (body.isEmpty()) {
            throw new MissingDescriptionException("delete");
        }
        try {
            int idx = Integer.parseInt(body) - 1;
            if (idx >= 0 && idx < tasks.getSize()) {
                Task removed = tasks.removeTask(idx);
                ui.showLine();
                System.out.println("Noted. I've removed this task:");
                System.out.println("  " + removed);
                String ts = tasks.getSize() > 1 ? "tasks" : "task";
                System.out.println("Now you have " + tasks.getSize() + " " + ts + " in the list.");
                ui.showLine();
            } else {
                throw new UnknownCommandException();
            }
        } catch (NumberFormatException e) {
            throw new ByteBuddyException("Error: Please provide a valid task number to delete.");
        }
    }
}