import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TodoList todoList = new TodoList();
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\n--- Todo List ---");
            System.out.println("1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Mark Task as Completed");
            System.out.println("4. Delete Task");
            System.out.println("5. Exit");
            System.out.print("\nEnter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("\nEnter task description: ");
                    String description = scanner.nextLine();
                    todoList.addTask(description, false);
                    break;
                case 2:
                    todoList.viewTasks();
                    break;
                case 3:
                    System.out.println("\nEnter task number to mark as completed: ");
                    int taskNumberIndex = scanner.nextInt();
                    todoList.markAsCompleted(taskNumberIndex);
                    break;
                case 4:
                    System.out.println("\nEnter task number to delete: ");
                    int taskNumberIndexDelete = scanner.nextInt();
                    todoList.deleteTask(taskNumberIndexDelete);
                    break;
                case 5:
                    isRunning = false;
                    System.out.println("\nExiting...");
                    break;
                default:
                    System.out.println("\nInvalid choice. Please try again.");
            }
        }
        scanner.close();
    }
}