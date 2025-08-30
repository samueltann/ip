package bytebuddy;

import bytebuddy.command.Command;
import bytebuddy.exception.ByteBuddyException;
import bytebuddy.parser.Parser;
import bytebuddy.storage.Storage;
import bytebuddy.task.TaskList;
import bytebuddy.ui.Ui;
import java.io.IOException;

/**
 * Represents the main ByteBuddy chatbot application.
 * Handles user input, executes commands, and manages the task list.
 */
public class ByteBuddy {

    private static final String FILE_PATH = "src/main/data/tasks.txt";

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public ByteBuddy(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.load();
        } catch (IOException e) {
            ui.showLoadingError();
            ui.printFarewell();
        }
    }

    public void run() {
        try {
            tasks = storage.load();
        } catch (IOException e) {
            System.out.println("Error loading tasks.txt file.");
            ui.printFarewell();
            return;
        }

        ui.printGreeting();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(storage, tasks, ui);
                isExit = c.isExit();
            } catch (ByteBuddyException e) {
                ui.printError(e.getMessage());
            } finally {
                storage.save(tasks);
            }
        }
    }

    public static void main(String[] args) {
        ByteBuddy buddy = new ByteBuddy(FILE_PATH);
        buddy.run();
    }
}