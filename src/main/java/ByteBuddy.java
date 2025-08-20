import java.util.ArrayList;
import java.util.Scanner;

public class ByteBuddy {
    public static final String LINE = "____________________________________________________________";

    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        printGreeting();
        String input;
        while (true) {
            input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("bye")) {
                printFarewell();
                break;
            } else if (input.equalsIgnoreCase("list")) {
                System.out.println(LINE);
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + "." + tasks.get(i));
                }
                System.out.println(LINE);
            } else if (input.startsWith("mark ")) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                if (index >= 0 && index < tasks.size()) {
                    tasks.get(index).markAsDone();
                    System.out.println(LINE);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  " + tasks.get(index));
                    System.out.println(LINE);
                }
            } else if (input.startsWith("unmark ")) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                if (index >= 0 && index < tasks.size()) {
                    tasks.get(index).markAsNotDone();
                    System.out.println(LINE);
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("  " + tasks.get(index));
                    System.out.println(LINE);
                }
            } else {
                Task newTask = new Task(input);
                tasks.add(newTask);
                System.out.println(LINE);
                System.out.println("added: " + input);
                System.out.println(LINE);
            }
        }

        scanner.close();
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
}