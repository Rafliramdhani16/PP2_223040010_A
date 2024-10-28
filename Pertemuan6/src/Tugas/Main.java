package Tugas;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
    public Main() {
        setTitle("Aplikasi Data Mahasiswa");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Membuat Menu Bar dengan warna latar belakang dan ukuran font
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(70, 130, 180));
        menuBar.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        // Membuat Menu dengan font khusus dan warna teks
        JMenu menu = new JMenu("Menu");
        menu.setForeground(Color.WHITE);
        menu.setFont(new Font("Arial", Font.BOLD, 14));
        menuBar.add(menu);

        // Membuat Menu Item untuk membuka FormInput
        JMenuItem menuFormInput = new JMenuItem("Form Input");
        menuFormInput.setFont(new Font("Arial", Font.PLAIN, 13));
        menuFormInput.addActionListener(e -> {
            FormInput formInput = new FormInput();
            formInput.setVisible(true);
        });
        menu.add(menuFormInput);

        // Membuat Menu Item untuk membuka ViewDataForm
        JMenuItem menuViewData = new JMenuItem("View Data");
        menuViewData.setFont(new Font("Arial", Font.PLAIN, 13));
        menuViewData.addActionListener(e -> {
            ViewDataForm viewDataForm = new ViewDataForm();
            viewDataForm.setVisible(true);
        });
        menu.add(menuViewData);

        setJMenuBar(menuBar);

        // Panel untuk menampung label di bawah menu
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Daftar Mahasiswa Universitas Pasundan", JLabel.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 20));
        titleLabel.setForeground(new Color(70, 130, 180));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        titlePanel.add(Box.createVerticalGlue());
        titlePanel.add(titleLabel);
        titlePanel.add(Box.createVerticalGlue());

        add(titlePanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main mainFrame = new Main();
            mainFrame.setVisible(true);
        });
    }
}
