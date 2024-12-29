package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class UserView extends JFrame {
    // Components for user input
    private JTextField txtName;
    private JTextField txtEmail;

    // Buttons
    private JButton btnAdd;
    private JButton btnRefresh;
    private JButton btnExport;
    private JButton btnLoading;

    // List components
    private JList<String> userList;
    private DefaultListModel<String> listModel;

    public UserView() {
        initializeComponents();
        setupLayout();
        setupFrame();
    }

    private void initializeComponents() {
        // Initialize text fields
        txtName = new JTextField(20);
        txtEmail = new JTextField(20);

        // Initialize buttons
        btnAdd = new JButton("Add User");
        btnRefresh = new JButton("Refresh");
        btnExport = new JButton("Export to PDF");
        btnLoading = new JButton("Show Loading");

        // Initialize list components
        listModel = new DefaultListModel<>();
        userList = new JList<>(listModel);
        userList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    private void setupLayout() {
        // Main panel with padding
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Input panel
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Add name components
        gbc.gridx = 0; gbc.gridy = 0;
        inputPanel.add(new JLabel("Name:"), gbc);
        gbc.gridx = 1; gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        inputPanel.add(txtName, gbc);

        // Add email components
        gbc.gridx = 0; gbc.gridy = 1;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        inputPanel.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1; gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        inputPanel.add(txtEmail, gbc);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnRefresh);
        buttonPanel.add(btnExport);
        buttonPanel.add(btnLoading);

        // Combine input and button panels
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(inputPanel, BorderLayout.CENTER);
        topPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add components to main panel
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(new JScrollPane(userList), BorderLayout.CENTER);

        // Set the main panel as content pane
        setContentPane(mainPanel);
    }

    private void setupFrame() {
        setTitle("User Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setMinimumSize(new Dimension(400, 300));
        setLocationRelativeTo(null); // Center on screen
    }

    // Getter methods for input fields
    public String getNameInput() {
        return txtName.getText().trim();
    }

    public String getEmailInput() {
        return txtEmail.getText().trim();
    }

    // Method to clear input fields
    public void clearInputs() {
        txtName.setText("");
        txtEmail.setText("");
        txtName.requestFocus();
    }

    // Method to update the user list
    public void setUserList(String[] users) {
        listModel.clear();
        for (String user : users) {
            listModel.addElement(user);
        }
    }

    // Listener attachment methods
    public void addAddUserListener(ActionListener listener) {
        btnAdd.addActionListener(listener);
    }

    public void addRefreshListener(ActionListener listener) {
        btnRefresh.addActionListener(listener);
    }

    public void addExportListener(ActionListener listener) {
        btnExport.addActionListener(listener);
    }

    public void addLoadingListener(ActionListener listener) {
        btnLoading.addActionListener(listener);
    }

    // Helper method to show error messages
    public void showError(String message) {
        JOptionPane.showMessageDialog(this,
                message,
                "Error",
                JOptionPane.ERROR_MESSAGE);
    }

    // Helper method to show success messages
    public void showSuccess(String message) {
        JOptionPane.showMessageDialog(this,
                message,
                "Success",
                JOptionPane.INFORMATION_MESSAGE);
    }
}