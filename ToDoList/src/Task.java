public class Task {
    private String description;
    private boolean isCompleted;

    public Task(String description, boolean isCompleted) {
        this.description = description;
        this.isCompleted = isCompleted;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted(){
        return isCompleted;
    }

    public void markedAsCompleted(){
        isCompleted = true;
    }

    public String toFileString() {
        return description + ":" + isCompleted;
    }

    @Override
    public String toString() {
        return (isCompleted ? "[x] " : "[_] ") + description; //If task is completed, then "[x]", else "[ ]".
    }

}
