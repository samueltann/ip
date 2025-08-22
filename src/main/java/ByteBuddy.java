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
            } else if (input.startsWith("todo ")) {
                String desc = input.substring(5).trim();
                System.out.println(desc);
                if (desc.isEmpty()) {
                    printLineMsg("Error: The description of a todo cannot be empty.");
                    continue;
                }
                Task t = new Todo(desc);
                tasks.add(t);
                printAdded(tasks, t);
            } else if (input.startsWith("deadline ")) {
                String body = input.substring(9).trim();
                if (body.isEmpty()) {
                    printLineMsg("Error: The description of a deadline cannot be empty.");
                    continue;
                }
                int byIdx = body.indexOf("/by");
                if (byIdx == -1) {
                    printLineMsg("Error:  Please provide a description and a '/by <time>'.");
                }
                String desc = body.substring(0, byIdx).trim();
                String by = body.substring(byIdx + 3).trim();
                Task t = new Deadline(desc, by);
                tasks.add(t);
                printAdded(tasks, t);
            } else if (input.startsWith("event ")) {
                String body = input.substring(6).trim();
                if (body.isEmpty()) {
                    printLineMsg("Error: The description of a event cannot be empty.");
                    continue;
                }
                int fromIdx = body.indexOf("/from");
                if (fromIdx == -1) {
                    printLineMsg("Error:  Please provide a description and a '/from ... /to ...'");
                }
                int toIdx = body.indexOf("/to");
                if (toIdx == -1) {
                    printLineMsg("Error:  Please provide a description and a '/from ... /to ...'");
                }
                String desc = body.substring(0, fromIdx).trim();
                String from = body.substring(fromIdx + 5, toIdx).trim();
                String to = body.substring(toIdx + 3).trim();
                Task t = new Event(desc, from, to);
                tasks.add(t);
                printAdded(tasks, t);
            } else {
                printLineMsg("Error: Please enter command todo / deadline / event");
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

    private static void printAdded(ArrayList<Task> tasks, Task t) {
        System.out.println(LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + t);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(LINE);
    }

    private static void printLineMsg(String msg) {
        System.out.println(LINE);
        System.out.println(msg);
        System.out.println(LINE);
    }

}