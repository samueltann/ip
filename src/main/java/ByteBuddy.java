import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ByteBuddy {
    public static final String LINE = "____________________________________________________________";
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
            return;
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
        String input;
        while (true) {
            input = ui.readCommand();
            try {
                if (input.equalsIgnoreCase("bye")) {
                    storage.save(tasks);
                    ui.printFarewell();
                    break;
                } else if (input.equalsIgnoreCase("list")) {
                    System.out.println(LINE);
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < tasks.getSize(); i++) {
                        System.out.println((i + 1) + ". " + tasks.getTask(i));
                    }
                    System.out.println(LINE);
                } else if (input.startsWith("mark ")) {
                    int index = Integer.parseInt(input.split(" ")[1]) - 1;
                    if (index >= 0 && index < tasks.getSize()) {
                        tasks.getTask(index).markAsDone();
                        System.out.println(LINE);
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println("  " + tasks.getTask(index));
                        System.out.println(LINE);
                    }
                } else if (input.startsWith("unmark ")) {
                    int index = Integer.parseInt(input.split(" ")[1]) - 1;
                    if (index >= 0 && index < tasks.getSize()) {
                        tasks.getTask(index).markAsNotDone();
                        System.out.println(LINE);
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println("  " + tasks.getTask(index));
                        System.out.println(LINE);
                    }
                } else if (input.startsWith("todo")) {
                    String desc = input.length() > 4 ? input.substring(4).trim() : "";
                    if (desc.isEmpty()) {
                        throw new MissingDescriptionException("todo");
                    }
                    Task t = new Todo(desc);
                    tasks.addTask(t);
                    ui.printAdded(tasks, t);
                } else if (input.startsWith("deadline")) {
                    String body = input.length() > 8 ? input.substring(8).trim() : "";
                    if (body.isEmpty()) {
                        throw new MissingDescriptionException("deadline");
                    }
                    int byIdx = body.indexOf("/by");
                    if (byIdx == -1) {
                        throw new MissingTimeException();
                    }
                    String desc = body.substring(0, byIdx).trim();
                    String by = body.substring(byIdx + 3).trim();
                    Task t = new Deadline(desc, by);
                    tasks.addTask(t);
                    ui.printAdded(tasks, t);
                } else if (input.startsWith("event")) {
                    String body = input.length() > 5 ? input.substring(5).trim() : "";
                    if (body.isEmpty()) {
                        throw new MissingDescriptionException("event");
                    }
                    int fromIdx = body.indexOf("/from");
                    if (fromIdx == -1) {
                        throw new MissingTimeException();
                    }
                    int toIdx = body.indexOf("/to");
                    if (toIdx == -1) {
                        throw new MissingTimeException();
                    }
                    String desc = body.substring(0, fromIdx).trim();
                    String from = body.substring(fromIdx + 5, toIdx).trim();
                    String to = body.substring(toIdx + 3).trim();
                    Task t = new Event(desc, from, to);
                    tasks.addTask(t);
                    ui.printAdded(tasks, t);
                } else if (input.startsWith("delete")) {
                    String body = input.length() > 6 ? input.substring(6).trim() : "";
                    if (body.isEmpty()) {
                        throw new MissingDescriptionException("delete");
                    }
                    try {
                        int idx = Integer.parseInt(body) - 1;
                        if (idx >= 0 && idx < tasks.getSize()) {
                            Task removed = tasks.removeTask(idx);
                            System.out.println(LINE);
                            System.out.println("Noted. I've removed this task:");
                            System.out.println("  " + removed);
                            String ts = tasks.getSize() > 1 ? "tasks" : "task";
                            System.out.println("Now you have " + tasks.getSize() + " " + ts + " in the list.");
                            System.out.println(LINE);
                        } else {
                            throw new ByteBuddyException("Error: That task number does not exist.");
                        }
                    } catch (NumberFormatException e) {
                        throw new ByteBuddyException("Error: Please provide a valid task number to delete.");
                    }
                } else {
                    throw new UnknownCommandException();
                }
            } catch (ByteBuddyException e) {
                ui.printError(e.getMessage());
            }
        }
    }
    public static void main(String[] args) {
        ByteBuddy buddy = new ByteBuddy(FILE_PATH);
        buddy.run();
    }
}