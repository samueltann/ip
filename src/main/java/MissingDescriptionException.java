public class MissingDescriptionException extends ByteBuddyException {
    public MissingDescriptionException(String taskType) {
        super("Error: The description of a " + taskType +  " cannot be empty.");
    }
}
