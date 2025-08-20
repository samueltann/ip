import java.util.Scanner;

public class ByteBuddy {
    public static final String line = "____________________________________________________________";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        printGreeting();
        String input;
        while (true) {
            input = scanner.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                printFarewell();
                break;
            }
            System.out.println(line);
            System.out.println(input);
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
