import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                TodoListApp app = new TodoListApp();
                app.setVisible(true);
            }
        });
    }
}