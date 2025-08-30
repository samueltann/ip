package bytebuddy.command;

import bytebuddy.storage.Storage;
import bytebuddy.task.Task;
import bytebuddy.task.TaskList;
import bytebuddy.task.TaskType;
import bytebuddy.ui.Ui;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class MarkCommandTest {

    // A simple fake Ui to capture output
    static class FakeUi extends Ui {
        private String lastMessage;

        @Override
        public void printMarked(Task task, boolean isDone) {
            lastMessage = "marked=" + isDone;
        }

        String getLastMessage() {
            return lastMessage;
        }
    }

    @Test
    void execute_validIndex_marksTaskAsDone() {
        TaskList tasks = new TaskList();
        Task t = new Task("read book", TaskType.TODO);
        tasks.addTask(t);

        FakeUi ui = new FakeUi();
        Storage storage = new Storage("src/test/data/tasks.txt");

        MarkCommand cmd = new MarkCommand(0);
        cmd.execute(storage, tasks, ui);

        assertEquals("marked=true", ui.getLastMessage());
    }

    @Test
    void execute_invalidIndex_doesNothing() {
        TaskList tasks = new TaskList();
        Task t = new Task("read book", TaskType.TODO);
        tasks.addTask(t);

        FakeUi ui = new FakeUi();
        Storage storage = new Storage("src/test/data/tasks.txt");

        MarkCommand cmd = new MarkCommand(5); // invalid index
        cmd.execute(storage, tasks, ui);

        assertNull(ui.getLastMessage(), "Ui should not print anything for invalid index");
    }
}
