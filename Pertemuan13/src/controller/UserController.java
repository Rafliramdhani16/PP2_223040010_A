package controller;

import model.User;
import model.UserMapper;
import view.UserView;
import view.UserPdf;
import org.apache.ibatis.session.SqlSession;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class UserController {
    private UserView view;
    private UserMapper mapper;
    private UserPdf pdf;
    private SqlSession session;
    private JProgressBar progressBar;
    private JLabel statusLabel;
    private JFrame loadingFrame;
    private JButton startButton;

    public UserController(UserView view, UserMapper mapper, UserPdf pdf, SqlSession session) {
        this.view = view;
        this.mapper = mapper;
        this.pdf = pdf;
        this.session = session;

        // Initialize loading components
        setupLoadingFrame();

        // Add listeners to view buttons
        this.view.addAddUserListener(new AddUserListener());
        this.view.addRefreshListener(new RefreshListener());
        this.view.addExportListener(new ExportListener());
        this.view.addLoadingListener(new LoadingListener());
    }

    private void setupLoadingFrame() {
        loadingFrame = new JFrame("Loading Status");
        loadingFrame.setSize(300, 200);
        loadingFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        loadingFrame.setLayout(new BorderLayout());

        // Setup status label
        statusLabel = new JLabel("Tekan tombol untuk mulai tugas berat", JLabel.CENTER);
        loadingFrame.add(statusLabel, BorderLayout.NORTH);

        // Setup progress bar
        progressBar = new JProgressBar(0, 100);
        progressBar.setValue(0);
        progressBar.setStringPainted(true);
        loadingFrame.add(progressBar, BorderLayout.CENTER);

        // Setup start button
        startButton = new JButton("Mulai");
        startButton.addActionListener(new StartButtonListener());
        loadingFrame.add(startButton, BorderLayout.SOUTH);
    }

    class StartButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            startButton.setEnabled(false);
            progressBar.setValue(0);

            new Thread(() -> {
                try {
                    // Simulate task with progress updates
                    for (int i = 0; i <= 100; i++) {
                        final int progress = i;
                        SwingUtilities.invokeLater(() -> {
                            progressBar.setValue(progress);
                        });
                        Thread.sleep(50); // Simulate work being done
                    }

                    SwingUtilities.invokeLater(() -> {
                        startButton.setEnabled(true);
                        loadingFrame.setVisible(false);
                    });
                } catch (Exception ex) {
                    SwingUtilities.invokeLater(() -> {
                        startButton.setEnabled(true);
                        JOptionPane.showMessageDialog(loadingFrame,
                                "Error during processing: " + ex.getMessage());
                    });
                }
            }).start();
        }
    }

    class LoadingListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            progressBar.setValue(0);
            startButton.setEnabled(true);
            loadingFrame.setLocationRelativeTo(view);
            loadingFrame.setVisible(true);
        }
    }

    class AddUserListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = view.getNameInput();
            String email = view.getEmailInput();

            if (!name.isEmpty() && !email.isEmpty()) {
                User user = new User();
                user.setName(name);
                user.setEmail(email);

                try {
                    mapper.insertUser(user);
                    session.commit();
                    view.clearInputs();
                    JOptionPane.showMessageDialog(view, "User added successfully!");
                    // Refresh the list after adding
                    refreshUserList();
                } catch (Exception ex) {
                    session.rollback();
                    JOptionPane.showMessageDialog(view,
                            "Error adding user: " + ex.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(view,
                        "Please fill in all fields.",
                        "Input Error",
                        JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    class RefreshListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            refreshUserList();
        }
    }

    private void refreshUserList() {
        try {
            List<User> users = mapper.getAllUsers();
            String[] userArray = users.stream()
                    .map(u -> u.getName() + " (" + u.getEmail() + ")")
                    .toArray(String[]::new);
            view.setUserList(userArray);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view,
                    "Error refreshing user list: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    class ExportListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                List<User> users = mapper.getAllUsers();
                pdf.exportPdf(users);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view,
                        "Error exporting to PDF: " + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}