package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class UserView extends JFrame {
    private JTextField txtName = new JTextField(20);
    private JTextField txtEmail = new JTextField(20);
    private JButton btnAdd = new JButton("Add");
    private JButton btnRefresh = new JButton("Refresh");
    private JButton btnExport = new JButton("Export");
    private JList<String> userList = new JList<>();
    private DefaultListModel<String> listModel = new DefaultListModel<>();

    public UserView() {
        setTitle("User Management");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel untuk input nama dan email
        JPanel panel = new JPanel(new GridLayout(5, 1));
        panel.add(new JLabel("Name:"));
        panel.add(txtName);
        panel.add(new JLabel("Email:"));
        panel.add(txtEmail);

        // Panel untuk tombol
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnRefresh);
        buttonPanel.add(btnExport);

        panel.add(buttonPanel);

        userList.setModel(listModel);
        add(panel, BorderLayout.NORTH);
        add(new JScrollPane(userList), BorderLayout.CENTER);
    }

    // Mendapatkan input nama
    public String getNameInput() {
        return txtName.getText();
    }
    public void clearInputs() {
        txtName.setText("");
        txtEmail.setText("");
    }
    // Mendapatkan input email
    public String getEmailInput() {
        return txtEmail.getText();
    }

    // Mengatur data pada JList
    public void setUserList(String[] users) {
        listModel.clear();
        for (String user : users) {
            listModel.addElement(user);
        }
    }

    // Menambahkan listener untuk tombol "Add"
    public void addAddUserListener(ActionListener listener) {
        btnAdd.addActionListener(listener);
    }

    // Menambahkan listener untuk tombol "Refresh"
    public void addRefreshListener(ActionListener listener) {
        btnRefresh.addActionListener(listener);
    }

    // Menambahkan listener untuk tombol "Export"
    public void addExportListener(ActionListener listener) {
        btnExport.addActionListener(listener);
    }
}
