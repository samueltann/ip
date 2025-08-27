import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate by;

    public Deadline(String description, String by, boolean isDone) {
        super(description, TaskType.DEADLINE, isDone);
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.by = LocalDate.parse(by, inputFormat);
    }

    public Deadline(String description, LocalDate by, boolean isDone) {
        super(description, TaskType.DEADLINE, isDone);
        this.by = by;
    }

    public Deadline(String description, String by) {
        this(description, by, false);
    }

    @Override
    public String toString() {
        return super.toString() + " | " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}