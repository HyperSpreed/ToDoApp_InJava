import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TodoListApp extends JFrame {
    private TodoList todoList;
    private DefaultListModel<String> listModel;
    private JList<String> taskList;
    private JTextField taskInput;
    private JTextField dueDateInput;

    public TodoListApp(){
        todoList = new TodoList();

        setTitle("To-do List App");
        setSize(500, 400);
        ImageIcon icon = new ImageIcon("todolisticon.png"); // Replace with your own icon file
        setIconImage(icon.getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        listModel = new DefaultListModel<>();
        taskList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(taskList);
        taskInput = new JTextField();
        dueDateInput = new JTextField();
        dueDateInput.setToolTipText("Enter due Date (yyyy-MM-dd)");
        JButton addTaskButton = new JButton("Add Task (Enter)");
        JButton completeTaskButton = new JButton("Mark as Completed (=)");
        JButton deleteTaskButton = new JButton("Delete Task (DEL)");

        ((JComponent) getContentPane()).setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        Font font = new Font("Roboto", Font.PLAIN, 14);
        taskInput.setFont(font);
        dueDateInput.setFont(font);
        taskList.setFont(font);

        getContentPane().setBackground(new Color(230,230,230));

        add(scrollPane, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3,1));
        inputPanel.add(new JLabel("Task Description: "));
        inputPanel.add(taskInput);
        inputPanel.add(new JLabel("Due Date: "));
        inputPanel.add(dueDateInput);
        add(inputPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addTaskButton);
        buttonPanel.add(completeTaskButton);
        buttonPanel.add(deleteTaskButton);
        add(buttonPanel, BorderLayout.SOUTH);

        refreshList();

        taskInput.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    addTask();
                }
            }
        });

        taskList.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_EQUALS) {
                    markTaskAsCompleted();
                } else if (e.getKeyCode() == KeyEvent.VK_DELETE) {
                    deleteTask();
                }
            }
        });

        addTaskButton.addActionListener(e -> addTask());
        completeTaskButton.addActionListener(e -> markTaskAsCompleted());
        deleteTaskButton.addActionListener(e -> deleteTask());
    }

    private void addTask() {
        String description = taskInput.getText().trim();
        String dueDateString = dueDateInput.getText().trim();
        LocalDate dueDate = null;

        if (!description.isEmpty()) {
            if (!dueDateString.isEmpty()) {
                try {
                    dueDate = LocalDate.parse(dueDateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                } catch (DateTimeParseException e) {
                    JOptionPane.showMessageDialog(this, "Invalid date format. Please use YYYY-MM-DD.");
                    return;
                }
            }
            todoList.addTask(description, false, dueDate);
            refreshList();
            taskInput.setText("");
            dueDateInput.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Please input a description in the text field.");
        }
    }

    private void markTaskAsCompleted() {
        int selectedIndex = taskList.getSelectedIndex();
        if (selectedIndex != -1) {
            todoList.markAsCompleted(selectedIndex + 1);
            refreshList();
        } else {
            JOptionPane.showMessageDialog(this, "Please select a task to mark as completed.");
        }
    }

    private void deleteTask() {
        int selectedIndex = taskList.getSelectedIndex();
        if (selectedIndex != -1) {
            todoList.deleteTask(selectedIndex + 1);
            refreshList();
        } else {
            JOptionPane.showMessageDialog(this, "Please select a task to delete.");
        }
    }

    public void refreshList() {
        listModel.clear();
        for (Task task : todoList.getTasks()) {
            listModel.addElement(task.toString());
        }
    }

}
