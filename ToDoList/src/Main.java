import java.security.spec.RSAOtherPrimeInfo;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> tasks = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\nTo-Do List Menu:");
            System.out.println("1. Add Task");
            System.out.println("2. Delete Task");
            System.out.println("3. View Tasks");
            System.out.println("4. Exit");
            System.out.println("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Enter task name: ");
                    String task = scanner.nextLine();
                    tasks.add(task);
                    System.out.println("Task added!");
                    break;
                case 2:
                    System.out.println("Select the task number to delete: ");
                    int taskNumber = scanner.nextInt();
                    if (taskNumber > 0 && taskNumber <= tasks.size()) {
                        tasks.remove(taskNumber - 1);
                        System.out.println("Task deleted!");
                    } else {
                        System.out.println("Invalid task number!");
                    }
                    break;
                case 3:
                    System.out.println("\nTasks:");
                    for (int i=0; i<tasks.size();i++){
                        System.out.println((i+1) + ". " + tasks.get(i));
                    }
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    isRunning = false;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}