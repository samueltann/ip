import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws IOException {
        File file = new File(filePath);
        ArrayList<Task> tasks = new ArrayList<>();
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
            tasks.add(t);
        }
        br.close();
        return tasks;
    }

    public void save(ArrayList<Task> tasks) {
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
        switch (type) {
            case "T":
                return new Todo(parts[2], isDone);
            case "D":
                return new Deadline(parts[2], parts[3], isDone);
            case "E":
                return new Event(parts[2], parts[3], parts[4], isDone);
            default:
                throw new RuntimeException("Corrupted save file!");
        }
    }
}
