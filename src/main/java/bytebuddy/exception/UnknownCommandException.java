package bytebuddy.exception;

public class UnknownCommandException extends ByteBuddyException {
    public UnknownCommandException() {
        super("Error: Please enter command todo / deadline <desc> /by <time> / event <desc> /from <time> /to <time>");
    }
}
