import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ByteBuddy {
    public static final String LINE = "____________________________________________________________";
    private static final String FILE_PATH = "../data/tasks.txt";

    public static void main(String[] args) {
        Storage storage = new Storage(FILE_PATH);
        ArrayList<Task> tasks;
        try {
            tasks = storage.load();
        } catch (IOException e) {
            System.out.println("Error loading tasks.txt file, starting fresh.");
            tasks = new ArrayList<>();
        }

        Scanner scanner = new Scanner(System.in);
        // ... existing loop ...
        // after every task modification:
        storage.save(tasks);
    }

    private static void printGreeting() {
        System.out.println(LINE);
        System.out.println("Hello! I'm ByteBuddy\n" + "What can I do for you?");
        System.out.println(LINE);
    }

    private static void printFarewell() {
        System.out.println(LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    private static void printAdded(ArrayList<Task> tasks, Task t) {
        System.out.println(LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + t);
        String ts = tasks.size() > 1 ? "tasks" : "task";
        System.out.println("Now you have " + tasks.size() + " " + ts + " in the list.");
        System.out.println(LINE);
    }

    private static void printLineMsg(String msg) {
        System.out.println(LINE);
        System.out.println(msg);
        System.out.println(LINE);
    }

}