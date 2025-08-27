import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public String getPath() {
        return filePath;
    }

    public TaskList load() throws IOException {
        File file = new File(filePath);
        TaskList tasks = new TaskList();
        if (!file.exists()) {
            file.getParentFile().mkdirs(); // make ./data if missing
            file.createNewFile();
            return tasks;
        }

        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(" \\| ");
            Task t = parse(parts);
            tasks.addTask(t);
        }
        br.close();
        return tasks;
    }

    public void save(TaskList tasks) {
        try{
        BufferedWriter bw = new BufferedWriter(new FileWriter(filePath));
        for (Task t : tasks) {
            bw.write(t.toString());
            bw.newLine();
        }
        bw.close();
        } catch(IOException e) {
            System.out.println("Error saving to file: " + e.getMessage());
        }
    }

    private Task parse(String[] parts) {
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        DateTimeFormatter inputFormat;
        switch (type) {
            case "T":
                return new Todo(parts[2], isDone);
            case "D":
                inputFormat = DateTimeFormatter.ofPattern("MMM d yyyy");
                LocalDate by = LocalDate.parse(parts[3], inputFormat);
                return new Deadline(parts[2], by, isDone);
            case "E":
                String[] timeframe = parts[3].split(" to ");
                inputFormat = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
                LocalDateTime from = LocalDateTime.parse(timeframe[0], inputFormat);
                LocalDateTime to = LocalDateTime.parse(timeframe[1], inputFormat);
                return new Event(parts[2], from, to, isDone);
            default:
                throw new RuntimeException("Corrupted save file!");
        }
    }
}
