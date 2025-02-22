import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Task {
    private String description;
    private boolean isCompleted;
    private LocalDate dueDate;

    public Task(String description, boolean isCompleted, LocalDate dueDate) {
        this.description = description;
        this.isCompleted = isCompleted;
        this.dueDate = dueDate;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted(){
        return isCompleted;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void markedAsCompleted(){
        isCompleted = true;
    }

    public String toFileString() {
        return description + ":" + isCompleted + ":" + (dueDate != null ? dueDate : "");
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dueDateString = (dueDate != null) ? "(Due: " + dueDate.format(formatter) + ")" : "";
        return (isCompleted ? "[x] " : "[_] ") + description + dueDateString; //If task is completed, then "[x]", else "[ ]".
    }

}
