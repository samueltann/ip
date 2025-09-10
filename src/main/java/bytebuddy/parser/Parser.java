package bytebuddy.parser;

import bytebuddy.command.AddCommand;
import bytebuddy.command.ByeCommand;
import bytebuddy.command.Command;
import bytebuddy.command.DeleteCommand;
import bytebuddy.command.FindCommand;
import bytebuddy.command.HelpCommand;
import bytebuddy.command.ListCommand;
import bytebuddy.command.MarkCommand;
import bytebuddy.command.UnknownCommand;
import bytebuddy.command.UnmarkCommand;
import bytebuddy.exception.MissingDescriptionException;
import bytebuddy.exception.MissingTimeException;
import bytebuddy.task.Deadline;
import bytebuddy.task.Event;
import bytebuddy.task.Task;
import bytebuddy.task.Todo;

public class Parser {
    public static Command parse(String input) {
        String trimmed = input.trim();
        String keyword = trimmed.contains(" ") ? trimmed.split(" ")[0] : trimmed;

        return switch (keyword.toLowerCase()) {
            case "bye" -> new ByeCommand();
            case "list" -> new ListCommand();
            case "mark" -> parseMark(trimmed);
            case "unmark" -> parseUnmark(trimmed);
            case "todo" -> parseTodo(trimmed);
            case "deadline" -> parseDeadline(trimmed);
            case "event" -> parseEvent(trimmed);
            case "delete" -> parseDelete(trimmed);
            case "find" -> parseFind(trimmed);
            case "help" -> parseHelp();
            default -> new UnknownCommand();
        };
    }
    private static Command parseMark(String input) {
        int index = Integer.parseInt(input.split(" ")[1]) - 1;
        return new MarkCommand(index);
    }
    private static Command parseUnmark(String input) {
        int index = Integer.parseInt(input.split(" ")[1]) - 1;
        return new UnmarkCommand(index);
    }

    private static Command parseTodo(String input) {
        String desc = input.length() > 4 ? input.substring(4).trim() : "";
        if (desc.isEmpty()) {
            throw new MissingDescriptionException("todo");
        }
        Task t = new Todo(desc);
        return new AddCommand(t);
    }

    private static Command parseDeadline(String input) {
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
        return new AddCommand(t);
    }

    private static Command parseEvent(String input) {
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
        String from = body.substring(fromIdx + 6, toIdx).trim();
        String to = body.substring(toIdx + 4).trim();
        Task t = new Event(desc, from, to);
        return new AddCommand(t);
    }

    private static Command parseDelete(String input) {
        String body = input.length() > 6 ? input.substring(6).trim() : "";
        return new DeleteCommand(body);
    }

    private static Command parseFind(String input) {
        String keyword = input.length() > 4 ? input.substring(5).trim() : "";
        return new FindCommand(keyword);
    }

    private static Command parseHelp() {
        return new HelpCommand();
    }
}
