import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.time.format.DateTimeParseException;

public class TodoList {
    private List<Task> tasks;
    private static final String FILE_PATH = "todo_list.txt";

    public TodoList(){
        tasks = new ArrayList<>();
        loadTasksFromFile();
    }

    public void addTask(String description, boolean isCompleted, LocalDate dueDate) {
        tasks.add(new Task(description, isCompleted, dueDate));
        saveTasksToFile();
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void viewTasks(){
        if(tasks.isEmpty()) {
            System.out.println("\nNo tasks found.");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }

    public void markAsCompleted(int taskNumber) {
        if(taskNumber >= 1 && taskNumber <= tasks.size()){
            tasks.get(taskNumber - 1).markedAsCompleted();
            saveTasksToFile();
        } else {
            System.out.println("Task number is invalid.");
        }
    }

    public void deleteTask(int taskNumber){
        if (taskNumber >= 1 && taskNumber <= tasks.size()){
            tasks.remove(taskNumber - 1);
            saveTasksToFile();
        } else {
            System.out.println("Task number is invalid.");
        }
    }

    private void saveTasksToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH));) {
            for (Task task : tasks) {
                writer.write(task.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    private void loadTasksFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));) {
            String line;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()){
                    String[] parts = line.split(":");
                    if (parts.length >= 2) {
                        String description = parts[0].trim();
                        boolean isCompleted = Boolean.parseBoolean(parts[1].trim());
                        LocalDate dueDate = null;
                        if (parts.length >= 3 && !parts[2].trim().isEmpty()) {
                            try {
                                dueDate = LocalDate.parse(parts[2].trim(), formatter);
                            } catch (DateTimeParseException e) {
                                System.out.println("Invalid due date format in file: " + parts[2]);
                            }
                        }
                        tasks.add(new Task(description, isCompleted, dueDate));
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("No existing tasks found or error in loading tasks: " + e.getMessage());
        }
    }

}
