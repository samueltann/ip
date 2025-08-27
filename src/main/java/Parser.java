public class Parser {
    public static Command parse(String input) {
            if (input.equalsIgnoreCase("bye")) {
                return new ByeCommand();
            } else if (input.equalsIgnoreCase("list")) {
                return new ListCommand();
            } else if (input.startsWith("mark ")) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                return new MarkCommand(index);
            } else if (input.startsWith("unmark ")) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                return new UnmarkCommand(index);
            } else if (input.startsWith("todo")) {
                String desc = input.length() > 4 ? input.substring(4).trim() : "";
                if (desc.isEmpty()) {
                    throw new MissingDescriptionException("todo");
                }
                Task t = new Todo(desc);
                return new AddCommand(t);
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
                return new AddCommand(t);
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
                return new AddCommand(t);
            } else if (input.startsWith("delete")) {
                String body = input.length() > 6 ? input.substring(6).trim() : "";
                return new DeleteCommand(body);
            } else {
                return new UnknownCommand();
            }
    }
}