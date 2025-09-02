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
 * <p>
 * This class is responsible for initializing the application, handling user input,
 * executing commands, and managing the task list.
 */
public class ByteBuddy {

    /** Default file path for storing tasks. */
    private static final String FILE_PATH = "src/main/data/tasks.txt";

    /** Handles storage and retrieval of tasks from disk. */
    private Storage storage;

    /** The list of tasks currently managed by the application. */
    private TaskList tasks;

    /** Handles user interaction, including input and output. */
    private Ui ui;

    /**
     * Constructs a new ByteBuddy chatbot instance.
     *
     * @param filePath the file path to load and save tasks
     */
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

    /**
     * Starts the chatbot, reads user commands, executes them, and updates storage.
     * <p>
     * This method runs the main loop of the application, displaying greetings,
     * handling user commands, catching exceptions, and saving the task list after
     * each command.
     */
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

    /**
     * The entry point of the ByteBuddy application.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        ByteBuddy buddy = new ByteBuddy(FILE_PATH);
        buddy.run();
    }
}
