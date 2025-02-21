import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TodoListApp extends JFrame {
    private TodoList todoList;
    private DefaultListModel<String> listModel;
    private JList<String> taskList;

    public TodoListApp(){
        todoList = new TodoList();

        setTitle("To-do List App");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        listModel = new DefaultListModel<>();
        taskList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(taskList);
        JTextField taskInput = new JTextField();
        JButton addTaskButton = new JButton("Add Task");
        JButton completeTaskButton = new JButton("Mark as Completed");
        JButton deleteTaskButton = new JButton("Delete Task");

        add(scrollPane, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());
        inputPanel.add(taskInput, BorderLayout.CENTER);
        inputPanel.add(addTaskButton, BorderLayout.EAST);
        add(inputPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(completeTaskButton);
        buttonPanel.add(deleteTaskButton);
        add(buttonPanel, BorderLayout.SOUTH);

        refreshList();

        addTaskButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String description = taskInput.getText().trim();
                if (!description.isEmpty()) {
                    todoList.addTask(description, false);
                    refreshList();
                    taskInput.setText("");
                } else {
                    JOptionPane.showMessageDialog(TodoListApp.this, "Please input a description in the text field.");
                }
            }
        });

        completeTaskButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = taskList.getSelectedIndex();
                if (selectedIndex != -1) {
                    todoList.markAsCompleted(selectedIndex + 1);
                    refreshList();
                } else {
                    JOptionPane.showMessageDialog(TodoListApp.this, "Please select a task to mark as completed.");
                }
            }
        });

        deleteTaskButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = taskList.getSelectedIndex();
                if (selectedIndex != -1) {
                    todoList.deleteTask(selectedIndex + 1);
                    refreshList();
                } else {
                    JOptionPane.showMessageDialog(TodoListApp.this, "Please select a task to delete.");
                }
            }
        });
    }

    public void refreshList() {
        listModel.clear();
        for (Task task : todoList.getTasks()) {
            listModel.addElement(task.toString());
        }
    }

}
