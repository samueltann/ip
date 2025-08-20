import java.util.ArrayList;
import java.util.Scanner;

public class ByteBuddy {
    public static final String line = "____________________________________________________________";

    public static void main(String[] args) {
        ArrayList<String> tasks = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        printGreeting();
        String input;
        while (true) {
            input = scanner.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                printFarewell();
                break;
            }
            if (input.equalsIgnoreCase("list")) {
                System.out.println(line);
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(i+1 + ". " + tasks.get(i));
                }
                System.out.println(line);
                continue;
            }
            tasks.add(input);
            System.out.println(line);
            System.out.println("added: " + input);
            System.out.println(line);
        }
        scanner.close();
    }

    private static void printGreeting() {
        System.out.println(line);
        System.out.println("Hello! I'm ByteBuddy\n" + "What can I do for you?");
        System.out.println(line);
    }

    private static void printFarewell() {
        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }
}