import java.util.Scanner;

public class Ui {
    public static final String LINE = "____________________________________________________________";
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine().trim();
    }

    public void printGreeting() {
        printLineMsg("Hello! I'm ByteBuddy\n" + "What can I do for you?");
    }

    public void printFarewell() {
        printLineMsg("Bye. Hope to see you again soon!");
        scanner.close();
    }

    public void showLoadingError() {
        System.out.println("Error loading tasks.txt file.");
    }

    public void printError(String msg) {
        printLineMsg(msg);
        scanner.close();
    }


    public void printAdded(TaskList tasks, Task t) {
        System.out.println(LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + t);
        String ts = tasks.getSize() > 1 ? "tasks" : "task";
        System.out.println("Now you have " + tasks.getSize() + " " + ts + " in the list.");
        System.out.println(LINE);
    }

    private void printLineMsg(String msg) {
        System.out.println(LINE);
        System.out.println(msg);
        System.out.println(LINE);
    }

}
