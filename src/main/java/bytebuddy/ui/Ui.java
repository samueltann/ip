package bytebuddy.ui;

import java.util.Scanner;

import bytebuddy.task.Task;
import bytebuddy.task.TaskList;

/**
 * Handles user interactions for the ByteBuddy application.
 * Provides methods for reading commands, printing messages,
 * and displaying task-related information.
 */
public class Ui {
    public static final String LINE = "____________________________________________________________";
    private final Scanner scanner;

    /**
     * Creates a new {@code Ui} instance with a {@link Scanner}
     * to read input from standard input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads the next line of user input and trims leading/trailing whitespace.
     *
     * @return the user input as a string.
     */
    public String readCommand() {
        return scanner.nextLine().trim();
    }

    /**
     * Prints a greeting message when the program starts.
     */
    public void printGreeting() {
        printLineMsg("Hello! I'm bytebuddy.ByteBuddy\n" + "What can I do for you?");
    }

    /**
     * Prints a farewell message when the program ends
     * and closes the scanner.
     */
    public void printFarewell() {
        printLineMsg("Bye. Hope to see you again soon!");
        scanner.close();
    }


    /**
     * Displays an error message indicating that the saved
     * tasks file could not be loaded.
     */
    public void showLoadingError() {
        System.out.println("Error loading tasks.txt file.");
    }


    /**
     * Prints a custom error message between divider lines
     * and closes the scanner.
     *
     * @param msg the error message to display.
     */
    public void printError(String msg) {
        printLineMsg(msg);
        scanner.close();
    }


    /**
     * Prints all tasks in the given {@link TaskList}.
     *
     * @param tasks the list of tasks to display.
     */
    public void printTaskList(TaskList tasks) {
        System.out.println(LINE);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.getSize(); i++) {
            System.out.println((i + 1) + ". " + tasks.getTask(i));
        }
        System.out.println(LINE);
    }


    /**
     * Prints a confirmation message when a task is marked
     * or unmarked as done.
     *
     * @param t      the task that was updated.
     * @param marked {@code true} if the task was marked as done,
     *               {@code false} if marked as not done.
     */
    public void printMarked(Task t, boolean marked) {
        System.out.println(LINE);
        if (marked) {
            System.out.println("Nice! I've marked this task as done:");
        } else {
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.println("  " + t);
        System.out.println(LINE);
    }

    /**
     * Prints a confirmation message when a new task is added.
     *
     * @param tasks the updated list of tasks.
     * @param t     the newly added task.
     */
    public void printAdded(TaskList tasks, Task t) {
        System.out.println(LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + t);
        String ts = tasks.getSize() > 1 ? "tasks" : "task";
        System.out.println("Now you have " + tasks.getSize() + " " + ts + " in the list.");
        System.out.println(LINE);
    }

    /**
     * Helper method to print a formatted message between divider lines.
     *
     * @param msg Message to print
     */
    private void printLineMsg(String msg) {
        System.out.println(LINE);
        System.out.println(msg);
        System.out.println(LINE);
    }

    /**
     * Prints a divider line.
     */
    public void showLine() {
        System.out.println(LINE);
    }

}
