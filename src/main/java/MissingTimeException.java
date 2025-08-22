public class MissingTimeException extends ByteBuddyException {
    public MissingTimeException() {
        super("Error: Please add a time");
    }
}
